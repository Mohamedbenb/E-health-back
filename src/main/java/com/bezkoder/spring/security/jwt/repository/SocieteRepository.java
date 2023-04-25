package com.bezkoder.spring.security.jwt.repository;


import com.bezkoder.spring.security.jwt.models.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface SocieteRepository extends JpaRepository<Societe,Long> {

    @Query("select n from Societe n where n.active = true")
    List<Societe> findAllByStatus();
}
