package com.Ehealth.spring.services;

import com.Ehealth.spring.models.ExamComp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamCompService {
    List<ExamComp> getall(boolean b);
    List<ExamComp> getbyemployee(Long empId, boolean b);
    ExamComp create(ExamComp examCompReq);
    ExamComp update(Long examCompId,boolean b, ExamComp examCompReq);
    ExamComp delete(Long examCompId, boolean b);
}
