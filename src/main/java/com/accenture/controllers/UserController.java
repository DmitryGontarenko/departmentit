package com.accenture.controllers;

import com.accenture.enums.Role;
import com.accenture.entity.user.User;
import com.accenture.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    // Эта аннатация будет для каждого метода в данном контроллере
    // проверять перед выполнением этого метода
    // наличие у пользователя прав, которые указаны в скобках
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user){

        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    // просмотр данных пользователя
    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal User user,
                             Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("firstName", user.getEmployee().getFirstName());
        model.addAttribute("lastName", user.getEmployee().getLastName());
        model.addAttribute("postId", user.getEmployee().getPostId().getName());
        model.addAttribute("subDivId", user.getEmployee().getSubDivId().getName());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email,
                                @RequestParam String firstName,
                                @RequestParam String lastName){

        userService.updateProfile(user, password, email, user.getEmployee(), firstName, lastName);
        return "redirect:/user/profile";
    }
}
