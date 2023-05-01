package com.Ehealth.spring.payload.dtos;

import com.Ehealth.spring.models.Societe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UniOpDto {

    private Long id;
    private String title;
    private Societe societe;
    private Boolean active = true;
}
