package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.TypeExam;
import com.Ehealth.spring.models.TypeVisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TypeExamRepository extends JpaRepository<TypeExam, Long > {
    List<TypeExam> findAllByActive(boolean b);

    Optional<TypeExam> findByIdAndActive(Long id, boolean b);
}
