package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findByTypeVisiteId(Long id);
}
