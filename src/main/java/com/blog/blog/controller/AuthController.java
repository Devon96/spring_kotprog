package com.blog.blog.controller;

import com.blog.blog.model.User;
import com.blog.blog.service.AuthoritiesService;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthController {

    UserService userService;
    AuthoritiesService authoritiesService;

    @Autowired
    public AuthController(UserService userService, AuthoritiesService authoritiesService) {
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Bejelentkezés");
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(User user, Model model){
        model.addAttribute("title", "Regisztráció");
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Regisztráció");
            return "register";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String userDisable(Model model){
        model.addAttribute("title", "Felhasználók");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("admin", authoritiesService.findByName("ADMIN"));
        return "users";
    }


    @GetMapping("/user/ban/{username}")
    public String banUser(@PathVariable String username){
        userService.banUser(username, false);
        return "redirect:/users";
    }

    @GetMapping("/user/enable/{username}")
    public String enableUser(@PathVariable String username){
        userService.banUser(username, true);
        return "redirect:/users";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
