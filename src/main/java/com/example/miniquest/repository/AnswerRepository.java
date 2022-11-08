package com.example.miniquest.repository;

import com.example.miniquest.model.Answer;
import com.example.miniquest.model.Question;
import com.example.miniquest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswersByUser_Id(Long id);

    List<Answer> findAnswerByUser_IdAndQuestion_Id(Long user_id, Long id);

    Optional<Answer> findOneByUserAndQuestion(User user, Question question);
}