package com.Ehealth.spring.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String text;
    private Date date;
    private boolean reply;
    // Add any additional fields as needed







    // Add getter and setter methods for additional fields
}
