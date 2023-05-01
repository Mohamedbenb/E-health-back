package com.Ehealth.spring.payload.dtos;

import com.Ehealth.spring.models.DateCal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateCalDto {
    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean draggable;
    private DateCal.Color color ;
    private Boolean active = true;
}
