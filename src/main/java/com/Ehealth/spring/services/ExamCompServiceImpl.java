package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.*;
import com.Ehealth.spring.payload.dtos.ValidateExamRequest;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.ExamCompRepository;
import com.Ehealth.spring.repository.TypeExamRepository;
import com.Ehealth.spring.repository.VisiteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExamCompServiceImpl implements ExamCompService{

    private final EmployeeRepository employeeRepository;
    private final ExamCompRepository examCompRepository;
    private final TypeExamRepository typeExamRepository;
    private final VisiteRepository visiteRepository;

    public ExamCompServiceImpl(EmployeeRepository employeeRepository, ExamCompRepository examCompRepository, TypeExamRepository typeExamRepository,
                               VisiteRepository visiteRepository) {
        super ();
        this.employeeRepository = employeeRepository;
        this.examCompRepository = examCompRepository;
        this.typeExamRepository = typeExamRepository;
        this.visiteRepository = visiteRepository;
    }

    @Override
    public List<ExamComp> getall(boolean b) {
        return examCompRepository.findByActive(b);
    }

    @Override
    public List<ExamComp> getbyemployee(Long empId, boolean b) {
        try{
            return examCompRepository.findByEmployeeIdAndActive(empId, b);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List <ExamComp> create( List <Long> employeeIds, List <Long> examIds, DateCal dateExam) {
        List <ExamComp> createdExams = new ArrayList<>();
        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.findByIdAndActive(employeeId, true)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + employeeId + " not found"));

            for (Long typeExamId : examIds) {
                TypeExam typeExam = typeExamRepository.findByIdAndActive(typeExamId, true)
                        .orElseThrow(() -> new ResourceNotFoundException("TypeVisit with ID " + typeExamId + " not found"));
                ExamComp newExamComp = new ExamComp();
                newExamComp.setEmployee(employee);
                newExamComp.setTypeExam(typeExam);
                newExamComp.setDateExam(dateExam);
                dateExam.setExamComp(newExamComp);
                examCompRepository.save(newExamComp);
                createdExams.add(newExamComp);
            }
        }
        return createdExams;


    }

    @Override
    public ExamComp update(Long examCompId, boolean b, ExamComp examCompReq, DateCal dateExam) {
        ExamComp examComp = new ExamComp();
        try{
         examComp    = examCompRepository.findByIdAndActive(examCompId, b).orElseThrow(() -> new ResourceNotFoundException("Examen" + examCompId + "NotFound"));
        }  catch (ResourceNotFoundException e) {
        throw new RuntimeException(e);
    }
        examComp.setDateExam(dateExam);


        examComp.setRappel(examCompReq.getRappel());

        return examCompRepository.save(examComp);

    }

    @Override
    public List<ExamComp> getunvalid(boolean b1, boolean b2) {
        return examCompRepository.findBydateExamActiveAndValid(b1,b2);
    }

    @Override
    public ExamComp ValidateExam(Long examId, ValidateExamRequest request) {
        System.out.println(request);
        ExamComp examComp = examCompRepository.findByIdAndActive(examId,true ).orElseThrow(() -> new ResourceNotFoundException("Examen" + examId + "NotFound"));
        examComp.setRecommendation(request.getRecommandation());
        examComp.setRappel(request.getDateReport());
        examComp.getDateExam().setActive(false);
        examComp.setDateValidation(new Date());
        examCompRepository.save(examComp);
        return examComp;
    }

    @Override
    public ExamComp delete(Long examCompId, boolean b) {
        ExamComp examComp = examCompRepository.findByIdAndActive(examCompId,b).orElseThrow(() -> new ResourceNotFoundException("Examen" + examCompId + "NotFound"));

        examComp.getDateExam().setActive(false);
        examComp.setActive(false);
        return examCompRepository.save(examComp);
    }
}
