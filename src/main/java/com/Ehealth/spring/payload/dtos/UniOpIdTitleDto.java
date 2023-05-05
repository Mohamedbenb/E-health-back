package com.Ehealth.spring.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniOpIdTitleDto {

    private Long id;
    private String title;
}
