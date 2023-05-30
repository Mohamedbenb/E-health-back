package com.Ehealth.spring.models;

import com.Ehealth.spring.models.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class ExamComp  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date rappel;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("exams")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)

    private DateCal dateExam;
    private String recommendation;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="exam_type_id", nullable=false)
    private TypeExam typeExam;

    private Date dateValidation;
    private boolean valid;
    private boolean active = true;
}
