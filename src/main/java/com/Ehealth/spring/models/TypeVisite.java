package com.Ehealth.spring.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class TypeVisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="type")
    private String type;


    @OneToMany(mappedBy = "primaryType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Visite> primaryVisites;

    @OneToMany(mappedBy = "secondaryType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Visite> secondaryVisites;

    private int frequency;
    @Column(name="remarque")
    private String remarque;
    @OneToOne
    private Color color;
    private boolean active = true;

}
