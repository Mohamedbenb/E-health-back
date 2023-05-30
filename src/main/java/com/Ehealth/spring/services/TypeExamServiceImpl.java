package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.TypeExam;

import com.Ehealth.spring.repository.ColorRepository;
import com.Ehealth.spring.repository.TypeExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeExamServiceImpl implements TypeExamService{

    private final TypeExamRepository typeExamRepository;
    private final ColorRepository colorRepository;

    public TypeExamServiceImpl(TypeExamRepository typeExamRepository, ColorRepository colorRepository) {
        this.typeExamRepository = typeExamRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    public List<TypeExam> getAllTypesExam() {
        return typeExamRepository.findAllByActive(true);
    }

    @Override
    public TypeExam getTypeExamById(Long id) {
        return typeExamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeExam with ID " + id + " not found"));
    }

    @Override
    public TypeExam createTypeExam(TypeExam typeExam) {
        colorRepository.save(typeExam.getColor());
        return typeExamRepository.save(typeExam);
    }

    @Override
    public TypeExam updateTypeExam(Long id, TypeExam typeExam) {

        TypeExam existingTypeExam = typeExamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeExam with ID " + id + " not found"));

        existingTypeExam.setType(typeExam.getType());

        existingTypeExam.setColor(typeExam.getColor());
        existingTypeExam.setFrequence(typeExam.getFrequence());

        return typeExamRepository.save(existingTypeExam);
    }

    @Override
    public void deleteTypeExam(Long id) {
        if (!typeExamRepository.existsById(id)) {
            throw new ResourceNotFoundException("TypeExam with ID " + id + " not found");
        }

        TypeExam typeExam = typeExamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeExam with ID " + id + " not found"));

        typeExam.setActive(false);
        typeExamRepository.save(typeExam);
    }
}
