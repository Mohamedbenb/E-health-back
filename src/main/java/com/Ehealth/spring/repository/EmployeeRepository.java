package com.Ehealth.spring.repository;

import com.Ehealth.spring.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select e from Employee e where e.active = true")
    List<Employee> findAllByStatus();

    List<Employee> findByUniopIdAndActive(Long employeeId, boolean a);
    Optional<Employee> findByIdAndUniopId(Long uniopId, long id);
}
