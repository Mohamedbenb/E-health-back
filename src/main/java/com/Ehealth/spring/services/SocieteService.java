package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Societe;

import java.util.List;

public interface SocieteService {

    List<Societe> getAllSocietes();

    Societe createSociete(Societe societe);

    Societe updateSociete(long id, Societe societe);

    Societe deleteSociete(long id);

    Societe getSocieteById(long id);
}

