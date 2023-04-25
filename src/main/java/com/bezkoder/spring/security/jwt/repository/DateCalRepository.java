package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.DateCal;
import com.bezkoder.spring.security.jwt.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateCalRepository extends JpaRepository<DateCal,Long> {
    @Query("select e from DateCal e where e.active = true")
    List<DateCal> findAllByStatus();
}
