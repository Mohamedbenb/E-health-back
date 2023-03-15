package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(long id, Employee employee);

    Employee deleteEmployee(long id);

    Employee getEmployeeById(long id);
}
