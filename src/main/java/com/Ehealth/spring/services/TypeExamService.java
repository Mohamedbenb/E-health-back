package com.Ehealth.spring.services;

import com.Ehealth.spring.models.TypeExam;
import com.Ehealth.spring.models.TypeVisite;

import java.util.List;

public interface TypeExamService {

    List<TypeExam> getAllTypesExam();
    TypeExam getTypeExamById(Long id);
    TypeExam createTypeExam(TypeExam typeExam);
    TypeExam updateTypeExam(Long id, TypeExam typeExam);
    void deleteTypeExam(Long id);
}
