package com.example.miniquest.service;

import com.example.miniquest.model.Answer;
import com.example.miniquest.model.Question;
import com.example.miniquest.model.QuestionForm;
import com.example.miniquest.model.User;
import com.example.miniquest.repository.AnswerRepository;
import com.example.miniquest.repository.QuestionRepository;
import com.example.miniquest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnsverService {
    @Autowired
    private AnswerRepository answersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public Model getAllUsers(Model model){
        return model.addAttribute("users", userRepository.findAll());
    }

    public List<Answer> checkAnswer(Long id){
        return answersRepository.findAnswersByUser_Id(id);
    }

    public Model listAnswers(Model model, List<Answer> answersFromBD) {

        Set<Question> listQuestion = new HashSet<>();
        answersFromBD.forEach((a) -> listQuestion.add(questionRepository.findQuestionById(a.question.getId())));

        model.addAttribute("questions", listQuestion);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findUserByUsername(currentPrincipalName);
        model.addAttribute("user", user);
        return model;
    }

    public Model listVariant(Long id, Long user_id, Model model) {
        QuestionForm questionnairesForm = new QuestionForm();
        List<Answer> answersFromBD = answersRepository.findAnswerByUser_IdAndQuestion_Id(user_id, id);
        Question questionnaires = questionRepository.findById(id).get();
        questionnairesForm.setQuestion(questionnaires);
        questionnairesForm.setAnswers(answersFromBD);

        model.addAttribute("questionForm", questionnairesForm);
        return model;
    }


}
