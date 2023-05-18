package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.payload.dtos.DateCalDto;
import com.Ehealth.spring.services.DateCalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/datecals")

public class DateCalController {
    @Autowired
    private ModelMapper modelMapper;
    private DateCalService datecalService;

    public DateCalController(DateCalService datecalService){
        super();
        this.datecalService = datecalService;
    }

    @GetMapping
    public List<DateCal> getAllDateCals() {

        return datecalService.getAllDateCals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateCal> getDateCalById(@PathVariable(name = "id") Long id) {
        DateCal datecal = datecalService.getDateCalById(id);

        // convert entity to DTO


        return ResponseEntity.ok().body(datecal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DateCal> createDateCal(@RequestBody DateCal datecalReq) {

        // convert DTO to entity

        DateCal datecal = datecalService.createDateCal(datecalReq);

        // convert entity to DTO


        return new ResponseEntity<DateCal>(datecal, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<DateCal> updateDateCal(@PathVariable long id, @RequestBody DateCal datecalRequest) {

        // convert DTO to Entity


        DateCal datecal = datecalService.updateDateCal(id, datecalRequest);

        // entity to DTO


        return ResponseEntity.ok().body(datecal);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteDateCal(@PathVariable(name = "id") Long id) {
        datecalService.deleteDateCal(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
