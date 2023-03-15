package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select e from Employee e where e.active = true")
    List<Employee> findAllByStatus();


}
