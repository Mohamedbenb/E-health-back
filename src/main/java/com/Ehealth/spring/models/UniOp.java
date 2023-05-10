package com.Ehealth.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@Data
@Entity
@Builder
public class UniOp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="title")
    private String title;
    private String reshum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="societe_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Societe societe;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="uniop")
    private List<Employee> employees ;
    public UniOp() {}
    @Column(name="active")
    private boolean active;
    public Boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
