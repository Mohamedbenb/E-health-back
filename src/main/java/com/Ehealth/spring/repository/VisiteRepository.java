package com.Ehealth.spring.repository;


import com.Ehealth.spring.models.Visite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisiteRepository extends JpaRepository<Visite,Long> {
    List<Visite> findByEmployeeId(Long id);

    List<Visite> findByPrimaryTypeIdAndValid(Long typeId, boolean b);
    List<Visite> findBySecondaryTypeId(Long typeId);
    List <Visite> findByValid(Boolean b);

    List <Visite> findByEmployeeIdAndValid(Long id, Boolean b);
    Optional<Visite> findBydatevisIdAndActive(Long dateVisid, boolean b);
    List <Visite> findBydatevisActiveAndValid(boolean b1, boolean b2);



}
