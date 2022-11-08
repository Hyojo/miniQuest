package com.example.miniquest.controller;

import com.example.miniquest.model.Answer;
import com.example.miniquest.service.AnsverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/answer")
@PreAuthorize("hasAuthority('ADMIN')")
public class AnswerController {
    @Autowired
    private AnsverService ansverService;

    @GetMapping("")
    public String getAllUsers(Model model) {
        model = ansverService.getAllUsers(model);
        return "users-list";
    }


    @GetMapping("/user/{id}")
    public String listAnswers(@PathVariable Long id, Model model) {
        List<Answer> answersFromBD = ansverService.checkAnswer(id);

        if (answersFromBD.isEmpty()) {
            model.addAttribute("message", "User did not answer questions");
            return "users-list";
        }
        model = ansverService.listAnswers(model, answersFromBD);
        return "save-question";
    }


    @GetMapping("/question/{id}/user/{user_id}")
    public String listVariant(@PathVariable Long id,
                              @PathVariable Long user_id,
                              Model model) {
        model = ansverService.listVariant(id, user_id, model);
        return "save-answers";
    }
}
