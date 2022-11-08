package com.example.miniquest.controller;

import com.example.miniquest.model.User;
import com.example.miniquest.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/registration/admin")
    public String registrationAdmin() {
        return "registration-admin";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFind = registrationService.findUser(user);
        if (userFind != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        model = registrationService.addUser(user, model);
        return "start-page";
    }

    @PostMapping("/registration/admin")
    public String addAdmin(User user, Model model) {
        User userFind = registrationService.findUser(user);
        if (userFind != null) {
            model.addAttribute("message", "User exists!");
            return "registration-admin";
        }
        model = registrationService.addAdmin(user, model);
        return "start-page";
    }
}
