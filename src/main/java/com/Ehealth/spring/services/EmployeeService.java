package com.Ehealth.spring.services;

import com.Ehealth.spring.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getbyuni(Long id, boolean b);

    Employee createEmployee(Long uniopId, Employee employee);

    Employee updateEmployee(long id, Employee employee);

    Employee deleteEmployee(long id);

    Employee getEmployeeById(Long uniopId,long id);
}
