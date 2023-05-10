package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.TypeVisite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeVisiteRepository extends JpaRepository<TypeVisite,Long> {
    List <TypeVisite> findAllByActive(boolean b);

    Optional<TypeVisite> findByIdAndActive(Long id, boolean b);
}
