package com.accenture.controllers;

import com.accenture.dao.Orders;
import com.accenture.dao.User;
import com.accenture.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private OrdersRepo ordersRepo;
    
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {

        // findAll возрвщает Iterable
        // findByTag возвращает List
        // List  наследуется от Iterable, поэтому используем его
        Iterable<Orders> orders = ordersRepo.findAll();

        // если строка фильтра не пустая - ищем по тегу
        // иначе, показываем все
        if (filter != null && !filter.isEmpty()) {
            orders = ordersRepo.findByTag(filter);
        } else {
            orders = ordersRepo.findAll();
        }

        model.addAttribute("orders", orders);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Orders order,
                      BindingResult bindingResult,
                      Model model) {

        order.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("order", order);
        } else { // сохранение только в том случае, если валидация пршла без ошибок

            // в случае если валидация прошла успешно, нужно удалить из модели order(сообщение)
            // иначе после добавления мы опять получим отрытую форму, в которой
            // будет выведено сообщение, которое мы только что попытались добавить
            model.addAttribute("order", null);
            ordersRepo.save(order);
        }

        // show
        Iterable<Orders> orders = ordersRepo.findAll();
        model.addAttribute("orders", orders);

        return "main";
    }




}
