package com.Ehealth.spring.services;

import com.Ehealth.spring.events.NewVisiteEvent;
import com.Ehealth.spring.events.VisiteEventPublisher;
import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.TypeVisite;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.TypeVisiteRepository;
import com.Ehealth.spring.repository.VisiteRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VisiteServiceImpl implements VisiteService{

    private final VisiteRepository visiteRepository;
    private final EmployeeRepository employeeRepository;
    private final TypeVisiteRepository visitTypeRepository;
    private final VisiteEventPublisher visiteEventPublisher;
    public VisiteServiceImpl(VisiteRepository visitRepository,
                            EmployeeRepository employeeRepository,
                            TypeVisiteRepository visitTypeRepository,
                             VisiteEventPublisher visiteEventPublisher) {
        this.visiteEventPublisher = visiteEventPublisher;
        this.visiteRepository = visitRepository;
        this.employeeRepository = employeeRepository;
        this.visitTypeRepository = visitTypeRepository;
    }

    @Override
    public List<Visite> createVisit(List<Long> employeeIds, List<Long> primaryTypeIds, Long secondTypeId, DateCal datevis) {
        List<Visite> createdVisits = new ArrayList<>();

        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.findByIdAndActive(employeeId, true)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + employeeId + " not found"));

            for (Long primaryTypeId : primaryTypeIds) {
                TypeVisite primaryType = visitTypeRepository.findByIdAndActive(primaryTypeId, true)
                        .orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + primaryTypeId + " not found"));

                Visite newVisite = new Visite();
                newVisite.setEmployee(employee);
                newVisite.setPrimaryType(primaryType);

                if (secondTypeId != null) {
                    TypeVisite secondaryType = visitTypeRepository.findByIdAndActive(secondTypeId, true)
                            .orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + secondTypeId + " not found"));
                    newVisite.setSecondaryType(secondaryType);
                }

                // Set the DateCal object for the visit
                newVisite.setDatevis(datevis);
                datevis.setVisite(newVisite);

                visiteRepository.save(newVisite);
                visiteEventPublisher.publishNewVisiteEvent(newVisite);

                createdVisits.add(newVisite);
            }
        }

        return createdVisits;
    }




    @Override
    public List<Visite> getAllVisits() {
        return visiteRepository.findAll();
    }

    @Override
    public List<Visite> getVisitsByEmployee(Long employeeId, Boolean b) {
        return visiteRepository.findByEmployeeIdAndValid(employeeId, b);
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
        Visite visite= visiteRepository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
        visite.getDatevis().setActive(false);
        visite.setActive(false);
        return visiteRepository.save(visite);
    }

    @Override
    public List<Visite> getunvalid(boolean b1, boolean b2) {
        return visiteRepository.findBydatevisActiveAndValid(b1, b2);
    }

    @Override
    public Visite ValidateVisite(Long visitId, String req) {
       Visite visite = visiteRepository.findById(visitId).orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
        visite.setValid(true);
        visite.getDatevis().setActive(false);
        visite.setRecommendation(req);
        visite.setDateValidation(new Date());
        visiteRepository.save(visite);
        return visite;
    }

    @Override
    public Visite getByDateVis(Long dateVisId, boolean b) {
        return visiteRepository.findBydatevisIdAndActive(dateVisId,b).orElseThrow(() -> new ResourceNotFoundException("Visit with ID  not found"));
    }

}
