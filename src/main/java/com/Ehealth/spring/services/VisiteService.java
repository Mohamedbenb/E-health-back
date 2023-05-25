package com.Ehealth.spring.services;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.models.Visite;

import java.time.LocalDate;
import java.util.List;

public interface VisiteService {
    List<Visite>  createVisit(List<Long> employeeIds, List<Long> primaryTypeIds, Long secondTypeId, DateCal datevis);
    List<Visite> getAllVisits();
    List<Visite> getVisitsByEmployee(Long employeeId, Boolean b);
    List<Visite> getVisitsByTypeVisite(Long visitTypeId);
    Visite getVisitById(Long visitId);
    Visite deleteVisite(Long visitId);
    List <Visite> getunvalid(boolean b1, boolean b2);
    Visite ValidateVisite(Long visitId, String visite);
    Visite getByDateVis(Long dateVisId, boolean b);

}
