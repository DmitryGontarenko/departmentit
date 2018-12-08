package com.accenture.controllers;

import com.accenture.entity.post.Post;
import com.accenture.entity.subdivision.SubDivision;
import com.accenture.entity.user.User;
import com.accenture.repository.post.PostRepo;
import com.accenture.repository.subdivision.SubDivRepo;
import com.accenture.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private SubDivRepo subDivRepo;

    @GetMapping("/registration")
    public String registration(Model model){

        Iterable<Post> post = postRepo.findAll();
        model.addAttribute("post", post);

        Iterable<SubDivision> subDivisions = subDivRepo.findAll();
        model.addAttribute("subDivisions", subDivisions);

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirm,
                          @RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("selectPost") Long selectPost,
                          @RequestParam("selectSubDiv") Long subDiv,
                          SubDivision subDivision,
                          Post post,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        // проверяем что password confirm не пустой
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        // проверяем, схоятся ли два пародя введенных при регистрации
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Password are different!");
            return "registration";
        }
        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }
        // если пользователь уже существует (false), сообщаем об этом на странице регистрации
        if (!userService.addUser(user, firstName, lastName, post, selectPost, subDivision, subDiv)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activated code is not found");
        }

        return "login";
    }
}
