package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.ExamComp;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.ExamCompRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamCompServiceImpl implements ExamCompService{

    private final EmployeeRepository employeeRepository;
    private final ExamCompRepository examCompRepository;

    public ExamCompServiceImpl(EmployeeRepository employeeRepository, ExamCompRepository examCompRepository) {
        super ();
        this.employeeRepository = employeeRepository;
        this.examCompRepository = examCompRepository;
    }

    @Override
    public List<ExamComp> getall(boolean b) {
        return examCompRepository.findByActive(b);
    }

    @Override
    public List<ExamComp> getbyemployee(Long empId, boolean b) {
        try{
            return examCompRepository.findByEmployeesIdAndActive(empId, b);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExamComp create( ExamComp examCompReq) {
        return examCompRepository.save(examCompReq);


    }

    @Override
    public ExamComp update(Long examCompId,boolean b, ExamComp examCompReq) {
        ExamComp examComp = new ExamComp();
        try{
         examComp    = examCompRepository.findByIdAndActive(examCompId, b).orElseThrow(() -> new ResourceNotFoundException("Examen" + examCompId + "NotFound"));
        }  catch (ResourceNotFoundException e) {
        throw new RuntimeException(e);
    }
        examComp.setTitle(examCompReq.getTitle());
        examComp.setFrequence(examCompReq.getFrequence());
        examComp.setRappel(examCompReq.getRappel());
        examComp.setRecommendation(examComp.getRecommendation());
        return examCompRepository.save(examComp);

    }

    @Override
    public ExamComp delete(Long examCompId, boolean b) {
        ExamComp examComp = examCompRepository.findByIdAndActive(examCompId,b).orElseThrow(() -> new ResourceNotFoundException("Examen" + examCompId + "NotFound"));
        examComp.setActive(false);
        return examCompRepository.save(examComp);
    }
}
