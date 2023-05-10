package com.Ehealth.spring.services;

import com.Ehealth.spring.models.TypeVisite;

import java.util.List;

public interface TypeVisiteService {

    List<TypeVisite> getAllTypesVisite();
    TypeVisite getTypeVisiteById(Long id);
    TypeVisite createTypeVisite(TypeVisite typeVisite);
    TypeVisite updateTypeVisite(Long id, TypeVisite typeVisite);
    void deleteTypeVisite(Long id);
}
