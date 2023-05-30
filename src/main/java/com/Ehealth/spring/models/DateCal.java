package com.Ehealth.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class DateCal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="start")
    private LocalDateTime start;
    @Column(name="end")
    private LocalDateTime end;
    @Column(name="draggable")
    private Boolean draggable = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Visite visite;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private ExamComp examComp;
    @Column(name="active")
    private boolean active;
    @OneToOne
    private Color color;




}
