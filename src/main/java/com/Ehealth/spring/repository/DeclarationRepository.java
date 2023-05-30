package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.UniOp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationRepository extends JpaRepository <Declaration,Long > {
    List<Declaration> findAllByActive(boolean b);
    List<Declaration> findByEmpIdAndActive(Long empi, boolean b);


    List<Declaration> findByMalIdAndActive(Long malId, boolean b);
}
