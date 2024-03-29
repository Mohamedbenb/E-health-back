package com.Ehealth.spring.services;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.models.Visite;

import java.time.LocalDate;
import java.util.List;

public interface VisiteService {
    List<Visite>  createVisit(List<Long> employeeIds, List<Long> primaryTypeIds, List<Long> visiteId, Long secondTypeId, DateCal datevis);
    List<Visite> getAllVisits();
    List<Visite> getVisitsByEmployee(Long employeeId, Boolean b);
    List<Visite> getVisitsByTypeVisite(Long visitTypeId, boolean b);
    Visite getVisitById(Long visitId,boolean b);
    Visite deleteVisite(Long visitId);
    List <Visite> getunvalid(boolean b1, boolean b2);
    Visite ValidateVisite(Long visitId, String visite);
    Visite getByDateVis(Long dateVisId, boolean b);
    List<Employee> getEmployees(Long visiteId, boolean b);
    List<Visite> getIncompleteSms(Long employeeId,Long primaryTypeId, boolean b);

}
