package com.example.miniquest.service;

import com.example.miniquest.model.*;
import com.example.miniquest.repository.AnswerRepository;
import com.example.miniquest.repository.QuestionRepository;
import com.example.miniquest.repository.UserRepository;
import com.example.miniquest.repository.VariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private VariantsRepository variantsRepository;


    public Model getAllQuestionnaires(Model model) {
        model.addAttribute("question", questionRepository.findAll());
        return model;
    }


    public Model listQuestion(Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthUserName = authentication.getName();
        User user = userRepository.findUserByUsername(AuthUserName);
        model.addAttribute("user", user);

        List<Answer> answers = new ArrayList<>();
        Iterable<Question> questionFromBD = questionRepository.findAll();
        for (Question question : questionFromBD) {
            Answer answer = answerRepository.findOneByUserAndQuestion(user, question)
                    .orElseGet(() -> {
                        Answer newAnswer = new Answer();
                        newAnswer.setUser(user);
                        newAnswer.setQuestion(question);
                        return newAnswer;
                    });
            answers.add(answer);
        }

        QuestionForm questionForm = new QuestionForm();

        questionForm.setQuestion(questionRepository.findQuestionById(id));
        questionForm.setAnswers(answers);
        model.addAttribute("questionForm", questionForm);
        return model;
    }


    public Model saveAnswer(QuestionForm questionForm, User user, Model model) {

        Variants variant = new Variants();

        for (Answer ans : questionForm.getAnswers()) {
            Answer answer = new Answer();
            for (Variants ansVar : ans.getVariants()) {
                variant = variantsRepository.findById(ansVar.getId()).get();
                answer.setQuestion(ansVar.getQuestion());
                answer.getVariants().add(ansVar);
                answer.setUser(user);
                variant.getAnswers().add(answer);
            }
            answerRepository.save(answer);
            variantsRepository.save(variant);
        }
        model.addAttribute("question", questionRepository.findAll());
        return model;
    }

}
