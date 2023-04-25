package com.bezkoder.spring.security.jwt.models;

import lombok.*;

import javax.persistence.*;



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
    @Column(name="username")
    private String username;


    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name="email", nullable = false)
    private String email;
    @Column(name = "age")
    private Long age;
    @Column(name="active")
    private boolean active;





}
