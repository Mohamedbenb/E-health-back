package com.Ehealth.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class TypeExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="type")
    private String type;

    private Long frequence;
    @OneToMany(mappedBy = "typeExam", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("typeExam")
    List<ExamComp>exams;

    @OneToOne
    private Color color;
    private boolean active = true;
}
