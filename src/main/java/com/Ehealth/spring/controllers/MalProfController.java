package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.MalProf;
import com.Ehealth.spring.models.MalProf;
import com.Ehealth.spring.models.Societe;
import com.Ehealth.spring.services.MalProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/malprof")
public class MalProfController {
    @Autowired
    private MalProfService malProfService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MalProf> createSociete(@RequestBody MalProf malProfreq) {
        MalProf malProf = malProfService.create(malProfreq);
        return new ResponseEntity<MalProf>(malProf, HttpStatus.CREATED);

    }
    @GetMapping
    public List<MalProf> getall()

    {
        return malProfService.getAll();
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<MalProf> getone(@PathVariable long id){
        MalProf malProfResponse =  malProfService.getone(id);
        return ResponseEntity.ok().body(malProfResponse);
    }
    @PatchMapping(value = "del/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        malProfService.delete(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PutMapping(value="ed/{id}")
    public ResponseEntity<MalProf> update( @PathVariable(value="id") long id, @RequestBody MalProf malProfRequest) {
        System.out.println(id);
        MalProf malProfResponse = malProfService.update( id, malProfRequest);

        return ResponseEntity.ok().body(malProfResponse);
    }
}
