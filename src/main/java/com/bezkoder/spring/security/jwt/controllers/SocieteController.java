package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.models.Societe;
import com.bezkoder.spring.security.jwt.payload.dtos.SocieteDto;
import com.bezkoder.spring.security.jwt.services.SocieteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/societes")

public class SocieteController {
    @Autowired
    private ModelMapper modelMapper;
    private SocieteService societeService;

    public SocieteController(SocieteService societeService){
        super();
        this.societeService = societeService;
    }

    @GetMapping
    public List<Societe> getAllSocietes() {

        return societeService.getAllSocietes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Societe> getSocieteById(@PathVariable(name = "id") Long id) {
        Societe societe = societeService.getSocieteById(id);

        // convert entity to DTO


        return ResponseEntity.ok().body(societe);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Societe> createSociete(@RequestBody Societe societeReq) {




        Societe societe = societeService.createSociete(societeReq);




        return new ResponseEntity<Societe>(societe, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<Societe> updateSociete(@PathVariable Long id, @RequestBody Societe societeReq) {

        // convert DTO to Entity


        Societe societe = societeService.updateSociete(id, societeReq);

        // entity to DTO


        return ResponseEntity.ok().body(societe);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteSociete(@PathVariable(name = "id") Long id) {
        societeService.deleteSociete(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
