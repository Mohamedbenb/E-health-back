package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.payload.dtos.VisitRequest;
import com.Ehealth.spring.services.VisiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/visites")
public class VisiteController {

    private final VisiteService visiteService;

    public VisiteController(VisiteService visiteService) {
        this.visiteService = visiteService;
    }

    @PostMapping
    public ResponseEntity<List<Visite>> createVisits(@RequestBody List<VisitRequest> visitRequests) {
        List<Visite> createdVisits = new ArrayList<>();

        for (VisitRequest request : visitRequests) {
            Long employeeId = request.getEmployeeId();
            Long primaryTypeId = request.getPrimaryTypeId();
            DateCal datevis = request.getDatevis();

            // Create the visit and add it to the list of created visits
            List<Visite> visits = visiteService.createVisit(Collections.singletonList(employeeId), Collections.singletonList(primaryTypeId), null, datevis);
            createdVisits.addAll(visits);
        }

        return ResponseEntity.ok(createdVisits);
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
        List<Visite> visites = visiteService.getVisitsByEmployee(employeeId, true);
        return ResponseEntity.ok(visites);
    }
    @PutMapping("/validate/{id}")
    public ResponseEntity<Visite> validateVisite( @PathVariable long id, @RequestBody String req) {



        Visite visiteResponse = visiteService.ValidateVisite( id, req);


        return ResponseEntity.ok().body(visiteResponse);
    }
    @GetMapping("/type/{visitTypeId}")
    public ResponseEntity<List<Visite>> getVisitsByTypeVisite(@PathVariable Long visitTypeId) {
        List<Visite> visites = visiteService.getVisitsByTypeVisite(visitTypeId, true);
        return ResponseEntity.ok(visites);
    }

    @PatchMapping("/{visitId}")
    public ResponseEntity<Void> deleteVisite(@PathVariable Long visitId) {
        visiteService.deleteVisite(visitId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/unvalid")
    public ResponseEntity<List<Visite>> getunvalid() {
        List<Visite> visites = visiteService.getunvalid(true, false);
        return ResponseEntity.ok(visites);
    }
    @GetMapping("/date/{id}")
    public ResponseEntity<Visite>getByDateVis(@PathVariable(name="id") Long id){
        Visite visite =visiteService.getByDateVis(id,true);
        return ResponseEntity.ok(visite);
    }
}

