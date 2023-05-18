package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.Color;
import com.Ehealth.spring.services.ColorService;
import com.Ehealth.spring.services.DateCalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {
    private ColorService colorService;

    public ColorController(ColorService colorService){
        super();
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> getall(){
        return colorService.getAll();
    }
    @PostMapping
    public ResponseEntity<Color> creat(@RequestBody Color colorReq){
        Color color = colorService.create(colorReq);
        return new ResponseEntity<Color>(color, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Color> getById(@PathVariable(value="id")Long id){
        Color color = colorService.getById(id);
        return ResponseEntity.ok().body(color);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Color> update(@PathVariable(value="id") Long id, @RequestBody Color colorreq){
        Color color = colorService.update(id,colorreq);
        return ResponseEntity.ok().body(color);
    }
}
