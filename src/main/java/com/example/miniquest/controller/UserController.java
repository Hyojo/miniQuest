package com.example.miniquest.controller;

import com.example.miniquest.model.QuestionForm;
import com.example.miniquest.model.User;
import com.example.miniquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllQuestionnaires(Model model) {
        model = userService.getAllQuestionnaires(model);
        return "user-questions";
    }


    @GetMapping("/question/{id}")
    public String listQuestion(@PathVariable Long id, Model model) {
        model = userService.listQuestion(id, model);
        return "variants-user";
    }

    @PostMapping("/save_answer")
    public String saveAnswer(@ModelAttribute("questionnairesForm") QuestionForm questionForm,
                             @ModelAttribute("user") User user, Model model) {
        model = userService.saveAnswer(questionForm, user, model);
        return "user-questions";
    }
}
