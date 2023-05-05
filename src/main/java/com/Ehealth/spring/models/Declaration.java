package com.Ehealth.spring.models;

import com.Ehealth.spring.models.inhertance.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Declaration extends BaseEntity {

    @ManyToOne

    private Employee emp;
    @ManyToOne
    private MalProf mal;
    private String typederisque;
    private String constat;
    private boolean depotcnam;
    private boolean diagnosticcnam;
    private String reponsecnam;
    private String remarque;

    private boolean active = true;

}
