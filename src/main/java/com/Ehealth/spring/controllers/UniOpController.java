package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.UniOp;
import com.Ehealth.spring.repository.UniOpRepository;
import com.Ehealth.spring.services.UniOpService;
import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.payload.dtos.UniOpDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")

public class UniOpController {
    @Autowired
    private ModelMapper modelMapper;
    private UniOpService uniOpService;
    @Autowired
    private UniOpRepository uniOpRepository;


    public UniOpController(UniOpService uniOpService){
        super();
        this.uniOpService = uniOpService;
    }

    @GetMapping("/societes/{societeId}/uniops")
    public List<UniOp> getAllUniOps(@PathVariable (value = "societeId") Long societeId) {

        return uniOpService.getAllUniOps(societeId);
    }

    @GetMapping("/uniops/{id}")
    public ResponseEntity<UniOp> getUniOpById(@PathVariable(value = "id") Long id) {
        UniOp uniOp = uniOpRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("uniOpId " + id + "not found"));

        // convert entity to DTO


        return ResponseEntity.ok().body(uniOp);
    }

    @PostMapping("/societes/{societeId}/uniops")
    public ResponseEntity<UniOpDto> createUniOp(@PathVariable (value = "societeId") Long societeId, @RequestBody UniOpDto uniOpDto) {

        // convert DTO to entity
        UniOp uniOpRequest = modelMapper.map(uniOpDto, UniOp.class);

        UniOp uniOp = uniOpService.createUniOp(societeId, uniOpRequest);

        // convert entity to DTO
        UniOpDto uniOpResponse = modelMapper.map(uniOp, UniOpDto.class);

        return new ResponseEntity<UniOpDto>(uniOpResponse, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/societes/{societeId}/{id}")
    public ResponseEntity<UniOp> updateUniOp(@PathVariable(value = "id") Long id, @PathVariable(value = "societeId") Long societeId, @RequestBody UniOp uniOpReq) {

        // convert DTO to Entity


        UniOp uniOp = uniOpService.updateUniOp(societeId ,id, uniOpReq);

        // entity to DTO


        return ResponseEntity.ok().body(uniOp);
    }

    @PatchMapping("/societes/{societeId}/{id}")
    public ResponseEntity<String> deleteUniOp(@PathVariable(value = "societeId") Long societeId, @PathVariable(value = "id") Long id ){
        uniOpService.deleteUniOp(societeId ,id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
