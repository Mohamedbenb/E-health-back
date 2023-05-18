package com.Ehealth.spring.models;

import com.Ehealth.spring.enume.Mainoeu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Mainoeu mainoeu;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Visite> visites = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="uniop_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private UniOp uniop;
    @Transient
    private Long idOp;
    @Transient
    private String uniopname;

    public void setActive(boolean active) {
        this.active = active;
    }


    public Long getIdOp() {
        return uniop.getId();
    }
    public String getUniopname(){
        return uniop.getTitle();
    }
}
