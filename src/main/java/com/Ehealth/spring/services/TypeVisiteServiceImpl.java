package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.repository.TypeVisiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeVisiteServiceImpl implements TypeVisiteService{
    private final TypeVisiteRepository typeVisiteRepository;
    public TypeVisiteServiceImpl(TypeVisiteRepository typeVisiteRepository) {
        this.typeVisiteRepository = typeVisiteRepository;

    }

    @Override
    public List<TypeVisite> getAllTypesVisite() {
        return typeVisiteRepository.findAllByActive(true);
    }

    @Override
    public TypeVisite getTypeVisiteById(Long id) {
        return typeVisiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeVisite with ID " + id + " not found"));
    }

    @Override
    public TypeVisite createTypeVisite(TypeVisite typeVisite) {
        return typeVisiteRepository.save(typeVisite);
    }

    @Override
    public TypeVisite updateTypeVisite(Long id, TypeVisite typeVisite) {
        TypeVisite existingTypeVisite = typeVisiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeVisite with ID " + id + " not found"));

        existingTypeVisite.setType(typeVisite.getType());
        existingTypeVisite.setFrequency(typeVisite.getFrequency());
        existingTypeVisite.setRemarque(typeVisite.getRemarque());

        return typeVisiteRepository.save(existingTypeVisite);
    }

    @Override
    public void deleteTypeVisite(Long id) {
        if (!typeVisiteRepository.existsById(id)) {
            throw new ResourceNotFoundException("TypeVisite with ID " + id + " not found");
        }

        TypeVisite typeVisite = typeVisiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeVisite with ID " + id + " not found"));

        typeVisite.setActive(false);
        typeVisiteRepository.save(typeVisite);
    }
}
