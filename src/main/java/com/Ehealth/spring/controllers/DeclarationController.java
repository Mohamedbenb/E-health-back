package com.Ehealth.spring.controllers;


import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.services.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/dec")
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;
    @PostMapping(value = "/{empid}/{malid}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Declaration> createSociete(@PathVariable(value = "empid") Long empid, @PathVariable (value = "malid") Long malid, @RequestBody Declaration declarationreq) {
        Declaration declaration = declarationService.create(empid, malid, declarationreq);
        return new ResponseEntity<Declaration>(declaration, HttpStatus.CREATED);

    }
    @GetMapping
    public List<Declaration> getall()

    {
        return declarationService.getall();
    };
    @GetMapping(value = "{id}")
    public ResponseEntity<Declaration> getone(@PathVariable long id){
        Declaration declarationResponse =  declarationService.getone(id);
        return ResponseEntity.ok().body(declarationResponse);
    };
    @PatchMapping(value = "del/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        declarationService.delete(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PutMapping(value="ed/{idemp}/{idmal}/{id}")
    public ResponseEntity<Declaration> updateEmployee( @PathVariable(value="id") long id, @PathVariable(value="idemp") long idemp,@PathVariable(value="idmal") long idmal, @RequestBody Declaration declarationRequest) {

        Declaration declarationResponse = declarationService.update(id, idemp, idmal, declarationRequest);

        return ResponseEntity.ok().body(declarationResponse);
    }
}
