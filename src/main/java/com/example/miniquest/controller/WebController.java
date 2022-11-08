package com.example.miniquest.controller;

import com.example.miniquest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    QuestionRepository questionRepository;


    @GetMapping("/")
    public String startPage() {
        return "start-page";
    }

    @GetMapping("/login")
    public String loginUsers() {
        return "login";
    }

}

