package com.example.miniquest.model;


import java.util.ArrayList;
import java.util.List;

public class QuestionForm {
    Question question;
    List<Answer> answers = new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
