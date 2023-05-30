package com.Ehealth.spring.payload.dtos;

import com.Ehealth.spring.models.DateCal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamRequest {
    public Long employeeId;
    public Long typeExamId;
    public DateCal dateExam;
    public DateCal datevis;
}
