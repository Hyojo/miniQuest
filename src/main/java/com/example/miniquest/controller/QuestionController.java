package com.example.miniquest.controller;


import com.example.miniquest.model.Question;
import com.example.miniquest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/question")
@PreAuthorize("hasAuthority('ADMIN')")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/create")
    public String create(Model model) {
        model = questionService.create(model);
        return "create-question";
    }

    @GetMapping("/show")
    public String question(Model model) {
        model = questionService.question(model);
        return "question-page";
    }

    @PostMapping("/create")
    public String addQuestion(@ModelAttribute("question") Question question, Model model) {
        Optional<Question> questionsFromBD = questionService.findQuestion(question);
        if (questionsFromBD.isPresent()) {
            model.addAttribute("message", "Questionnaire exists!");
            return "create-question";
        }
        model = questionService.addQuestion(question, model);
        return "question-page";
    }

    @RequestMapping("/{id}")
    public String patchQuestion(@ModelAttribute("question") Question question,
                                @PathVariable Long id, Model model) {
        model = questionService.patchQuestion(question, id, model);
        return "question-page";
    }

    @GetMapping("/edit/{id}")
    public String listQuestionEdit(@PathVariable Long id, Model model) {
        model = questionService.listQuestionEdit(id, model);
        return "/edit-question";
    }

    @RequestMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        model = questionService.deleteQuestion(id, model);
        return "question-page";
    }

    @GetMapping("/{id}/variants")
    public String listVariant(@PathVariable Long id, Model model) {
        model = questionService.listVariant(id, model);
        return "variants";
    }
}
