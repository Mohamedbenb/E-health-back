package com.Ehealth.spring.repository;


import com.Ehealth.spring.models.Visite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisiteRepository extends JpaRepository<Visite,Long> {
    List<Visite> findByEmployeeId(Long id);

    List<Visite> findByPrimaryTypeId(Long typeId);
    List<Visite> findBySecondaryTypeId(Long typeId);
    List <Visite> findByValid(Boolean b);



}
