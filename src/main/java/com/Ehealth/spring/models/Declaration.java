package com.Ehealth.spring.models;

import com.Ehealth.spring.models.inhertance.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Declaration  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Employee emp;
    @ManyToOne
    private MalProf mal;
    private Date dateDec;
    private String typederisque;
    private String constat;
    private boolean depotcnam;
    private boolean diagnosticcnam;
    private String reponsecnam;
    private String remarque;

    private boolean active = true;

}
