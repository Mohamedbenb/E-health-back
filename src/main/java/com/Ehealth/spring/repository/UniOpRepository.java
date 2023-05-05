package com.Ehealth.spring.repository;


import com.Ehealth.spring.models.UniOp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UniOpRepository extends JpaRepository<UniOp,Long> {

    @Query("select n from UniOp n where n.active = true")
    List<UniOp> findAllByActive();
    List<UniOp> findBySocieteId(Long uniopId);
    Optional<UniOp> findByIdAndSocieteId( Long societeId, Long id);
}
