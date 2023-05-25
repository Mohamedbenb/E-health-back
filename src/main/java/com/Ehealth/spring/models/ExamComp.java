package com.Ehealth.spring.models;

import com.Ehealth.spring.models.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String title;
    private String frequence;
    private String rappel;
    @ManyToMany(fetch = FetchType.EAGER,
                cascade={
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },
                mappedBy = "exams")
    //@JsonIgnoreProperties("exams")
    private Set<Employee> employees= new HashSet<>();
    private String recommendation;
    private boolean active = true;
}
