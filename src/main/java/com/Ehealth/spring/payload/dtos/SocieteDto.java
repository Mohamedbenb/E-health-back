package com.Ehealth.spring.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SocieteDto {

    private Long id;
    private String title;
    private Boolean active = true;
}
