package com.Ehealth.spring.payload.dtos;

import java.time.LocalDate;

public class VisiteRequest {
    private Long employeeId;
    private Long primaryTypeId;
    private Long secondaryTypeId;
    private LocalDate visitDate;
    private String recommendation;

    // Getters and setters for the fields
}