package com.Ehealth.spring.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private Long matricule;
    private Date daterecru;
    private String email;
    private String age;
    private Boolean active = true;
        }
