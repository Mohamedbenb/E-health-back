package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee createEmployee(Long uniopId, Employee employee);

    Employee updateEmployee(Long uniopId,long id, Employee employee);

    Employee deleteEmployee(Long uniopId,long id);

    Employee getEmployeeById(Long uniopId,long id);
}
