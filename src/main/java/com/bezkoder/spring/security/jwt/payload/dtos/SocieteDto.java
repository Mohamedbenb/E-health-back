package com.bezkoder.spring.security.jwt.payload.dtos;

import com.bezkoder.spring.security.jwt.models.UniOp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SocieteDto {

    private Long id;
    private String title;
    private Boolean active = true;
}
