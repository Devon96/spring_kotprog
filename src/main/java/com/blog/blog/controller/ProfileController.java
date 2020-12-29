package com.blog.blog.controller;


import com.blog.blog.model.User;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/profile")
public class ProfileController {
    UserService userService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{profilname}")
    public String profile(Model model, @PathVariable String profilname){
        User user = userService.findByUsername(profilname);
        model.addAttribute("user", user);
        model.addAttribute("title", user.getUsername());
        if(user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            return "my-profile";
        }
        return "profile";
    }

    @PostMapping("/edit/{username}")
    public String editProfile(@RequestParam("profilImage") MultipartFile profilImage, User user, @PathVariable String username){
        User userUpdate = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        
        /*
        if(userUpdate.getPassword().equals(passwordEncoder.encode(user.getOldPassword()))){
            System.out.println("MEGEGGYEZIK");
            userUpdate.setPassword(passwordEncoder.encode(user.getNewPassword()));
        }
        */


        userUpdate.setBirthday(user.getBirthday());
        userUpdate.setEmail(user.getEmail());

        userService.update(userUpdate, profilImage);

        return "redirect:/profile/" + username;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
