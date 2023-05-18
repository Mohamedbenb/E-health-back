package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.models.Visite;

import java.time.LocalDate;
import java.util.List;

public interface VisiteService {
    Visite  createVisit(Long employeeid, Long primtypeId, Long secondtypeId, Visite visite);
    List<Visite> getAllVisits();
    List<Visite> getVisitsByEmployee(Long employeeId);
    List<Visite> getVisitsByTypeVisite(Long visitTypeId);
    Visite getVisitById(Long visitId);
    Visite deleteVisite(Long visitId);
    List <Visite> getunvalid(boolean b);
    Visite ValidateVisite(Long visitId, String visite);

}
