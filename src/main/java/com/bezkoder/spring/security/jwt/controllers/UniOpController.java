package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.exception.ResourceNotFoundException;
import com.bezkoder.spring.security.jwt.models.UniOp;
import com.bezkoder.spring.security.jwt.payload.dtos.UniOpDto;
import com.bezkoder.spring.security.jwt.repository.UniOpRepository;
import com.bezkoder.spring.security.jwt.services.UniOpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
