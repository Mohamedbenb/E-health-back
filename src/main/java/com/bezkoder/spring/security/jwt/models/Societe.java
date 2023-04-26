package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name="active")
    private boolean active = true;
    public Boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
