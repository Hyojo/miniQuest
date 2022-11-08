package com.example.miniquest.repository;

import com.example.miniquest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findQuestionByNameQuestion(String nameQuestion);

    Question findQuestionByVariantsId(Long id);

    Question findQuestionById(Long id);

}