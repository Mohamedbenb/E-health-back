package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.payload.dtos.VisiteRequest;
import com.Ehealth.spring.services.VisiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/visites")
public class VisiteController {

    private final VisiteService visiteService;

    public VisiteController(VisiteService visiteService) {
        this.visiteService = visiteService;
    }

    @PostMapping
    public ResponseEntity<Visite> createVisit(@RequestParam("employeeId") Long employeeId,
                                              @RequestParam("primaryTypeId") Long primaryTypeId,
                                              @RequestParam(value = "secondaryTypeId", required = false) Long secondaryTypeId,
                                              @RequestBody Visite visiterequest) {
        Visite visite = visiteService.createVisit(employeeId, primaryTypeId, secondaryTypeId, visiterequest);
        return ResponseEntity.ok(visiterequest);
    }

    @GetMapping
    public ResponseEntity<List<Visite>> getAllVisits() {
        List<Visite> visites = visiteService.getAllVisits();
        return ResponseEntity.ok(visites);
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<Visite> getVisitById(@PathVariable Long visitId) {
        Visite visite = visiteService.getVisitById(visitId);
        return ResponseEntity.ok(visite);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Visite>> getVisitsByEmployee(@PathVariable Long employeeId) {
        List<Visite> visites = visiteService.getVisitsByEmployee(employeeId);
        return ResponseEntity.ok(visites);
    }
    @PutMapping("/validate/{id}")
    public ResponseEntity<Visite> validateVisite( @PathVariable long id, @RequestBody String req) {



        Visite visiteResponse = visiteService.ValidateVisite( id, req);


        return ResponseEntity.ok().body(visiteResponse);
    }
    @GetMapping("/type/{visitTypeId}")
    public ResponseEntity<List<Visite>> getVisitsByTypeVisite(@PathVariable Long visitTypeId) {
        List<Visite> visites = visiteService.getVisitsByTypeVisite(visitTypeId);
        return ResponseEntity.ok(visites);
    }

    @DeleteMapping("/{visitId}")
    public ResponseEntity<Void> deleteVisite(@PathVariable Long visitId) {
        visiteService.deleteVisite(visitId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/unvalid")
    public ResponseEntity<List<Visite>> getunvalid() {
        List<Visite> visites = visiteService.getunvalid(false);
        return ResponseEntity.ok(visites);
    }
}

