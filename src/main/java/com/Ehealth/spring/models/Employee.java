package com.Ehealth.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="postetrav")
    private String postetrav;
    @Column(name="matricule")
    private Long matricule;
    @Column(name="daterecru")
    private LocalDate daterecru;
    @Column(name="datenai")
    private LocalDate datenai;
    @Column(name="email")
    private String email;
    @Column(name = "age")
    private Long age;
    @Column(name = "numdosmed")
    private Long numdosmed;
    @Column(name="active")
    private boolean active = true;
    @Column(name="status")
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="uniop_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UniOp uniop;
    public void setActive(boolean active) {
        this.active = active;
    }





}
