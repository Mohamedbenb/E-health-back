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
    Long x;
    @Override
    public List<Visite> createVisit(List<Long> employeeIds, List<Long> primaryTypeIds, List<Long> visiteIds, Long secondTypeId, DateCal datevis) {
        System.out.println("visiteIds"+visiteIds);
        List<Visite> createdVisits = new ArrayList<>();

        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.findByIdAndActive(employeeId, true)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + employeeId + " not found"));
            if (!visiteIds.isEmpty())
            {
                for (Long visiteId : visiteIds) {

                    System.out.println("checkpoint0  " );
                    Visite visite = visiteRepository.findByIdAndValid(visiteId, true).orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visiteId + " not found"));
                    x=visite.getEmployee().getId();
                    System.out.println("checkpoint 1" );


                    visite.getDatevis().setActive(true);
                    System.out.println("checkpoint2" +visite.getDatevis().isActive());
                    visite.getDatevis().setStart(datevis.getStart());
                    System.out.println("checkpoint3 "+ visite.getDatevis().getStart());
                    visite.getDatevis().setEnd(datevis.getEnd());
                    System.out.println("checkpoint4 "+ visite.getDatevis().getEnd());
                    visite.setValid(false);
                    System.out.println("checkpoint5 "+visite.isValid());
                    visiteRepository.save(visite);

                }
            }

            for (Long primaryTypeId : primaryTypeIds) {
                if(employeeId!=x){
                    System.out.println("checkpoint2  " + primaryTypeId);
                    TypeVisite primaryType = visitTypeRepository.findByIdAndActive(primaryTypeId, true)
                            .orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + primaryTypeId + " not found"));

                    Visite newVisite = new Visite();

                    newVisite.setEmployee(employee);
                    newVisite.setPrimaryType(primaryType);
                    System.out.println("checkpoint2  " + datevis);
                    if (secondTypeId != null) {
                        TypeVisite secondaryType = visitTypeRepository.findByIdAndActive(secondTypeId, true)
                                .orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + secondTypeId + " not found"));
                        newVisite.setSecondaryType(secondaryType);
                    }

                    // Set the DateCal object for the visit

                    newVisite.setDatevis(datevis);
                    datevis.setVisite(newVisite);

                    visiteRepository.save(newVisite);


                    createdVisits.add(newVisite);

                }
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
    public List<Visite> getVisitsByTypeVisite(Long visitTypeId, boolean b) {
        return visiteRepository.findByPrimaryTypeIdAndValid(visitTypeId, b);
    }

    @Override
    public Visite getVisitById(Long visitId, boolean b) {
        return visiteRepository.findByIdAndValid(visitId,b)
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
        List <Visite> visites_final = new ArrayList<>();
        List<Visite> visites=visiteRepository.findByActive(b1);
        for (Visite visite : visites)
              { if (visite.getPrimaryType().getType().toLowerCase().equals("sms")){
                  if(visite.getDateValidation2()==null)
                  {
                      visites_final.add(visite);
                  }
              }
            
        }
        return visiteRepository.findBydatevisActiveAndValid(b1, b2);
    }

    @Override
    public Visite ValidateVisite(Long visitId, String req) {
       Visite visite = visiteRepository.findById(visitId).orElseThrow(() -> new ResourceNotFoundException("Visit with ID " + visitId + " not found"));
        visite.setValid(true);
        visite.getDatevis().setActive(false);
        visite.setRecommendation(req);
        if(visite.getDateValidation()==null) {
            visite.setDateValidation(new Date());
        }
        else{
            visite.setDateValidation2(new Date());
        }
        visiteRepository.save(visite);
        return visite;
    }

    @Override
    public Visite getByDateVis(Long dateVisId, boolean b) {
        return visiteRepository.findBydatevisIdAndActive(dateVisId,b).orElseThrow(() -> new ResourceNotFoundException("Visit with ID  not found"));
    }

    @Override
    public List<Employee> getEmployees(Long visiteId,boolean b) {
        return employeeRepository.findByVisitesIdAndActive(visiteId, b);
    }

    @Override
    public List<Visite> getIncompleteSms(Long employeeId, Long primaryTypeId, boolean b) {
        List<Visite> visite_fin = new ArrayList<>();
        List<Visite> visites_init = visiteRepository.findByEmployeeIdAndPrimaryTypeIdAndValid(employeeId, primaryTypeId, b);
        for (Visite visite:visites_init){
            if (visite.getDateValidation2()==null){
                visite_fin.add(visite);
            }
        }
        return visite_fin;
    }

}
