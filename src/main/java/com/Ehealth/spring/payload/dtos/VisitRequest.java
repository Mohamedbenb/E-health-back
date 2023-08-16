package com.Ehealth.spring.payload.dtos;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.Visite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitRequest {
    private Long employeeId;
    private Long primaryTypeId;
    private Long secondTypeId;
    private DateCal datevis;
    private List<Long> visiteIds;
}
