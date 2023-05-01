package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Societe;
import com.Ehealth.spring.repository.SocieteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocieteServiceImpl implements SocieteService {

    private final SocieteRepository societeRepository;
    public SocieteServiceImpl(SocieteRepository SocieteRepository) {
        super();
        this.societeRepository = SocieteRepository;
    }
    @Override
    public List<Societe> getAllSocietes(){

        return societeRepository.findAllByStatus();
    }
    @Override
    public Societe createSociete(Societe Societe){
        return societeRepository.save(Societe);
    }
    @Override
    public Societe updateSociete(long id, Societe societeRequest){
        Societe Societe = null;
        try {
            Societe = societeRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Societe"+id+ "id"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        Societe.setTitle(societeRequest.getTitle());
        Societe.setMat(societeRequest.getMat());
        Societe.setTel(societeRequest.getTel());
        Societe.setFax(societeRequest.getFax());
        Societe.setAdresse(societeRequest.getAdresse());
        Societe.setCodepostale(societeRequest.getCodepostale());
        return societeRepository.save(Societe);
    }

    @Override
    public Societe deleteSociete(long id) {
        Societe Societe = null;

        try {
            Societe = societeRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("Societe"+id+ "id"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        Societe.setActive(false);
        return societeRepository.save(Societe);

    }

    @Override
    public Societe getSocieteById(long id) {
        Optional<Societe> result = societeRepository.findById(id);
        if(result.isPresent()){
            return result.get();

        }else{
            try {
                throw new ResourceNotFoundException("Societe"+id+"id");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
