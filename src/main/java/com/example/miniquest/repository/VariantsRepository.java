package com.example.miniquest.repository;

import com.example.miniquest.model.Variants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariantsRepository extends JpaRepository<Variants, Long> {
    Iterable<Variants> findAllByQuestion_Id(Long id);

    Optional<Variants> findVariantsByNameVariantAndQuestion_Id(String nameVariant, Long id);

    List<Variants> findVariantsByQuestion_Id(Long id);
}