package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Color;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.repository.ColorRepository;
import com.Ehealth.spring.repository.TypeVisiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeVisiteServiceImpl implements TypeVisiteService{
    private final TypeVisiteRepository typeVisiteRepository;
    private final ColorRepository colorRepository;

    public TypeVisiteServiceImpl(TypeVisiteRepository typeVisiteRepository,
                                 ColorRepository colorRepository) {
        this.typeVisiteRepository = typeVisiteRepository;

        this.colorRepository = colorRepository;
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
        colorRepository.save(typeVisite.getColor());
        return typeVisiteRepository.save(typeVisite);
    }

    @Override
    public TypeVisite updateTypeVisite(Long id, TypeVisite typeVisite) {
        //Color color = colorRepository.findById(colorid).orElseThrow(()->new ResourceNotFoundException("id"+colorid+"id"));
        TypeVisite existingTypeVisite = typeVisiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeVisite with ID " + id + " not found"));

        existingTypeVisite.setType(typeVisite.getType());
        existingTypeVisite.setFrequency(typeVisite.getFrequency());
        existingTypeVisite.setRemarque(typeVisite.getRemarque());
        existingTypeVisite.setColor(typeVisite.getColor());

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
