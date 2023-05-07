package com.Ehealth.spring.repository;


import com.Ehealth.spring.models.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface SocieteRepository extends JpaRepository<Societe,Long> {

    @Query("SELECT DISTINCT s FROM Societe s LEFT JOIN FETCH s.uniops u WHERE s.active = true AND (u.active = true OR u IS NULL)")
    List<Societe> findAllByStatus();
    List<Societe> findByActiveTrueAndUniopsActiveTrueOrUniopsIsNull();
}
