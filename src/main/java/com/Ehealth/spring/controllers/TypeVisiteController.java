package com.Ehealth.spring.controllers;


import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.services.TypeVisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typevisites")
public class TypeVisiteController {
    @Autowired
    TypeVisiteService typeVisiteService;

    public TypeVisiteController(TypeVisiteService typeVisiteService){
        super();
        this.typeVisiteService = typeVisiteService;
    }
    @GetMapping
    public List<TypeVisite> getAllTypesVisite(){
        return typeVisiteService.getAllTypesVisite();
    }
    @GetMapping("/{id}")
    public ResponseEntity <TypeVisite> getTypeVisiteById(@PathVariable(name="id") Long id){
        TypeVisite typeVisite = typeVisiteService.getTypeVisiteById(id);
        return ResponseEntity.ok().body(typeVisite);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeVisite> createTypeVisite(@RequestBody TypeVisite typeVisiteReq) {
        TypeVisite typeVisite = typeVisiteService.createTypeVisite(typeVisiteReq);
        return new ResponseEntity<TypeVisite>(typeVisite, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<TypeVisite> updateSociete(@PathVariable Long id, @RequestBody TypeVisite typeVisiteReq) {

        TypeVisite typeVisite = typeVisiteService.updateTypeVisite(id, typeVisiteReq);
        return ResponseEntity.ok().body(typeVisite);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteTypeVisite(@PathVariable(name = "id") Long id) {
        typeVisiteService.deleteTypeVisite(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
