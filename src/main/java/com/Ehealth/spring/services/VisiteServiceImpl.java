package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.TypeVisiteRepository;
import com.Ehealth.spring.repository.VisiteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisiteServiceImpl implements VisiteService{

    private final VisiteRepository visiteRepository;
    private final EmployeeRepository employeeRepository;
    private final TypeVisiteRepository visitTypeRepository;

    public VisiteServiceImpl(VisiteRepository visitRepository,
                            EmployeeRepository employeeRepository,
                            TypeVisiteRepository visitTypeRepository) {
        this.visiteRepository = visitRepository;
        this.employeeRepository = employeeRepository;
        this.visitTypeRepository = visitTypeRepository;
    }

    @Override
    public Visite createVisit(Long employeeid, Long primtypeId, Long secondtypeId, Visite visite) {
        Employee employee = employeeRepository.findByIdAndActive(employeeid, true).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + employeeid + " not found"));

        TypeVisite primaryType = visitTypeRepository.findByIdAndActive(primtypeId, true).orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + primtypeId + " not found"));
        if (secondtypeId!=null) {
            TypeVisite secondaryType = visitTypeRepository.findByIdAndActive(secondtypeId, true).orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + secondtypeId + " not found"));

            visite.setSecondaryType(secondaryType);
        }
        visite.setPrimaryType(primaryType);
        visite.setEmployee(employee);

        visiteRepository.save(visite);

        return visite;
    }


    @Override
    public List<Visite> getAllVisits() {
        return visiteRepository.findAll();
    }

    @Override
    public List<Visite> getVisitsByEmployee(Long employeeId) {
        return visiteRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Visite> getVisitsByTypeVisite(Long visitTypeId) {
        return visiteRepository.findByPrimaryTypeId(visitTypeId);
    }

    @Override
    public Visite getVisitById(Long visitId) {
        return visiteRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
    }

    @Override
    public Visite deleteVisite(Long visitId) {
        return visiteRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
    }

    @Override
    public List<Visite> getunvalid(boolean b) {
        return visiteRepository.findByValid(b);
    }

    @Override
    public Visite ValidateVisite(Long visitId, String req) {
       Visite visite = visiteRepository.findById(visitId).orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
        visite.setValid(true);
        visite.getDatevis().setActive(false);
        visite.setRecommendation(req);
        visiteRepository.save(visite);
        return visite;
    }

}
