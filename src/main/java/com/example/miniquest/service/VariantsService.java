package com.example.miniquest.service;

import com.example.miniquest.model.Question;
import com.example.miniquest.model.Variants;
import com.example.miniquest.repository.QuestionRepository;
import com.example.miniquest.repository.VariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class VariantsService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private VariantsRepository variantsRepository;


    public Model createVariant(Long id, Model model) {
        model.addAttribute("variant", new Variants());
        Question question = questionRepository.findById(id).get();
        model.addAttribute("question", question);
        return model;
    }


    public Model addVariant(Variants variant, Question question, Model model) {
        variant.setNameVariant(variant.getNameVariant());
        variantsRepository.save(variant);
        model.addAttribute("variants", variantsRepository.findAllByQuestion_Id(question.getId()));
        return model;
    }

    public Model listVariantEdit(Long id, Model model) {
        Variants variant = variantsRepository.findById(id).get();
        model.addAttribute("variant", variant);
        return model;
    }

    public Model patchVariant(Long id, Variants variant, Model model) {
        Question question = questionRepository.findQuestionByVariantsId(id);
        Variants variantFromDB = variantsRepository.findById(id).get();
        variantFromDB.setNameVariant(variant.getNameVariant());
        variantsRepository.save(variantFromDB);
        model.addAttribute("question", question);
        model.addAttribute("variants", variantsRepository.findAllByQuestion_Id(question.getId()));
        return model;
    }


    public Model deleteVariant(Long id, Model model) {
        Question question = questionRepository.findQuestionByVariantsId(id);
        Variants variantFromDB = variantsRepository.findById(id).get();
        variantsRepository.delete(variantFromDB);
        model.addAttribute("question", question);
        model.addAttribute("variants", variantsRepository.findAllByQuestion_Id(question.getId()));
        return model;
    }

    public Optional<Variants> findVariant(Variants variant, Question question) {
        return variantsRepository.findVariantsByNameVariantAndQuestion_Id(variant.getNameVariant(), question.getId());
    }
}
