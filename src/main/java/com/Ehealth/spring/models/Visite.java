package com.Ehealth.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="recommendation")
    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("visites")
    private Employee employee;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="primary_type_id", nullable=false)


    private TypeVisite primaryType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="secondary_type_id")


    private TypeVisite secondaryType;

    @OneToOne(cascade = CascadeType.ALL)

    private DateCal datevis;


    private Date dateValidation;
    private Date dateValidation2;
    @Column(name = "valid")
    private boolean valid;

    private boolean active = true;
}
