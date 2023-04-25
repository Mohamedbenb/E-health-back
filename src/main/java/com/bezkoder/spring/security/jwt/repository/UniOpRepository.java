package com.bezkoder.spring.security.jwt.repository;


import com.bezkoder.spring.security.jwt.models.Societe;
import com.bezkoder.spring.security.jwt.models.UniOp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UniOpRepository extends JpaRepository<UniOp,Long> {

    @Query("select n from UniOp n where n.active = true")
    List<UniOp> findAllByStatus();
    List<UniOp> findBySocieteId(Long uniopId);
    Optional<UniOp> findByIdAndSocieteId( Long societeId, Long id);
}
