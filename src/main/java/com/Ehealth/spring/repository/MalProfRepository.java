package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.Declaration;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.models.MalProf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  MalProfRepository extends JpaRepository<MalProf,Long> {
    List<MalProf> findAllByActive(boolean b);
}
