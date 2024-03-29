package com.Ehealth.spring.payload.dtos;

import com.Ehealth.spring.models.Societe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniOpDto {

    private Long id;
    private String title;
    private SocieteDto societeDto;
    private Boolean active = true;
    private List<EmployeeDto> employeeDtos;
}
