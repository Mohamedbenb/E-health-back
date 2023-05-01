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
    public List<DateCalDto> getAllDateCals() {

        return datecalService.getAllDateCals().stream().map(

                        datecal -> modelMapper.map(datecal, DateCalDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateCalDto> getDateCalById(@PathVariable(name = "id") Long id) {
        DateCal datecal = datecalService.getDateCalById(id);

        // convert entity to DTO
        DateCalDto datecalResponse = modelMapper.map(datecal, DateCalDto.class);

        return ResponseEntity.ok().body(datecalResponse);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DateCalDto> createDateCal(@RequestBody DateCalDto datecalDto) {

        // convert DTO to entity
        DateCal datecalRequest = modelMapper.map(datecalDto, DateCal.class);

        DateCal datecal = datecalService.createDateCal(datecalRequest);

        // convert entity to DTO
        DateCalDto datecalResponse = modelMapper.map(datecal, DateCalDto.class);

        return new ResponseEntity<DateCalDto>(datecalResponse, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<DateCalDto> updateDateCal(@PathVariable long id, @RequestBody DateCalDto datecalDto) {

        // convert DTO to Entity
        DateCal datecalRequest = modelMapper.map(datecalDto, DateCal.class);

        DateCal datecal = datecalService.updateDateCal(id, datecalRequest);

        // entity to DTO
        DateCalDto datecalResponse = modelMapper.map(datecal, DateCalDto.class);

        return ResponseEntity.ok().body(datecalResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteDateCal(@PathVariable(name = "id") Long id) {
        datecalService.deleteDateCal(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
