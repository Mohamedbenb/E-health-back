package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.models.Societe;

import java.util.List;

public interface SocieteService {

    List<Societe> getAllSocietes();

    Societe createSociete(Societe employee);

    Societe updateSociete(long id, Societe employee);

    Societe deleteSociete(long id);

    Societe getSocieteById(long id);
}

