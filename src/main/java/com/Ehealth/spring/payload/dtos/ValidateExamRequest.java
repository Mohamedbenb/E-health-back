package com.Ehealth.spring.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidateExamRequest {
    private Date dateReport;
    private String recommandation;
}
