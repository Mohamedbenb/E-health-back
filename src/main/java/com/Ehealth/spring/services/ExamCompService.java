package com.Ehealth.spring.services;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.ExamComp;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.payload.dtos.ValidateExamRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamCompService {
    List<ExamComp> getall(boolean b);
    List<ExamComp> getbyemployee(Long empId, boolean b);
    List <ExamComp> create(List <Long> employeeIds, List <Long> examIds, DateCal dateExam);
    ExamComp update(Long examCompId,boolean b, ExamComp examCompReq, DateCal dateExam);
    List <ExamComp> getunvalid(boolean b1, boolean b2);
    ExamComp ValidateExam(Long examId, ValidateExamRequest request);
    ExamComp delete(Long examCompId, boolean b);
}
