package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.ExamComp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamCompRepository extends JpaRepository <ExamComp,Long> {

    List<ExamComp> findByActive(boolean b);
    List<ExamComp> findByEmployeeIdAndActive(Long empId, boolean b);
    Optional<ExamComp> findByIdAndActive(Long examCompId, boolean b);
    List <ExamComp> findBydateExamActiveAndValid(boolean b1, boolean b2);
}
