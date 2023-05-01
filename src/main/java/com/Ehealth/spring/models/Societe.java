package com.Ehealth.spring.models;

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
@Data
@Entity
@Builder
public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name = "Matricule")
    private Long mat;
    @Column(name = "Tel")
    private Long tel;
    @Column(name = "Fax")
    private Long fax;
    @Column(name="Adresse")
    private String adresse;
    @Column(name="CodePostale")
    private String codepostale;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="societe")
    private Set<UniOp> uniops = new HashSet<>();
    @Column(name="active")
    private boolean active = true;
    public Boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
