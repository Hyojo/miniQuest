package com.example.miniquest.service;

import com.example.miniquest.model.Question;
import com.example.miniquest.model.Variants;
import com.example.miniquest.repository.QuestionRepository;
import com.example.miniquest.repository.VariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private VariantsRepository variantsRepository;

    public Model create(Model model) {
        model.addAttribute("question", new Question());
        return model;
    }

    public Model question(Model model) {

        List<Question> questionFromDB = questionRepository.findAll();
        if (questionFromDB.isEmpty()) {
            model.addAttribute("message", "Lise is empty");
        } else {
            model.addAttribute("questions", questionRepository.findAll());
        }

        return model;
    }

    public Model addQuestion(Question question, Model model) {

        question.setNameQuestion(question.getNameQuestion());
        questionRepository.save(question);
        model.addAttribute("questions", questionRepository.findAll());
        return model;
    }

    public Optional<Question> findQuestion(Question question) {
        return questionRepository.findQuestionByNameQuestion(question.getNameQuestion());
    }

    public Model patchQuestion(Question question, Long id, Model model) {
        Question questionFromDB = questionRepository.findById(id).get();
        questionFromDB.setNameQuestion(question.getNameQuestion());
        questionFromDB.setType(question.getType());
        questionRepository.save(questionFromDB);
        model.addAttribute("questions", questionRepository.findAll());
        return model;
    }

    public Model listQuestionEdit(Long id, Model model) {
        Question question = questionRepository.findById(id).get();
        model.addAttribute("question", question);
        return model;
    }

    public Model deleteQuestion(Long id, Model model) {
        Question questionFromDB = questionRepository.findQuestionById(id);
        questionRepository.delete(questionFromDB);
        model.addAttribute("questions", questionRepository.findAll());
        return model;
    }

    public Model listVariant(Long id, Model model) {
        List<Variants> variantsList = variantsRepository.findVariantsByQuestion_Id(id);
        Iterable<Variants> variants = variantsRepository.findAllByQuestion_Id(id);
        Question question = questionRepository.findQuestionById(id);

        if (variantsList.isEmpty()) {
            model.addAttribute("question", question);
            model.addAttribute("message", "Lise is empty");
        } else {
            model.addAttribute("question", question);
            model.addAttribute("variants", variants);
        }
        return model;
    }
}

