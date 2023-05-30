package com.Ehealth.spring.controllers;


import com.Ehealth.spring.models.TypeExam;
import com.Ehealth.spring.services.TypeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeexams")
public class TypeExamController {
    @Autowired
    TypeExamService typeExamService;
    public TypeExamController(TypeExamService typeExamService){
        super();
        this.typeExamService=typeExamService;
    }
    @GetMapping
    public List<TypeExam> getAllTypesExam(){
        return typeExamService.getAllTypesExam();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TypeExam> getTypeExamById(@PathVariable(name="id") Long id){
        TypeExam typeExam = typeExamService.getTypeExamById(id);
        return ResponseEntity.ok().body(typeExam);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeExam> createTypeExam(@RequestBody TypeExam typeExamReq) {
        TypeExam typeExam = typeExamService.createTypeExam(typeExamReq);
        return new ResponseEntity<TypeExam>(typeExam, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<TypeExam> updateSociete(@PathVariable Long id,  @RequestBody TypeExam typeExamReq) {

        TypeExam typeExam = typeExamService.updateTypeExam(id, typeExamReq );
        return ResponseEntity.ok().body(typeExam);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteTypeExam(@PathVariable(name = "id") Long id) {
        typeExamService.deleteTypeExam(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
