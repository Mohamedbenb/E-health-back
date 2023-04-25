package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.exception.ResourceNotFoundException;
import com.bezkoder.spring.security.jwt.models.Employee;
import com.bezkoder.spring.security.jwt.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAllByStatus();
    }
    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public Employee updateEmployee(long id, Employee employeeRequest){
        Employee employee = null;
        try {
            employee = employeeRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Employee"+id+ "id"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        employee.setFirstname(employeeRequest.getFirstname());
        employee.setLastname(employeeRequest.getLastname());
        employee.setUsername(employeeRequest.getUsername());
        employee.setEmail(employeeRequest.getEmail());
        employee.setAge(employeeRequest.getAge());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee deleteEmployee(long id) {
        Employee employee = null;

        try {
            employee = employeeRepository.findById(id)

                    .orElseThrow(()-> new ResourceNotFoundException("Employee"+id+"id"));

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        employee.setActive(false);
        return employeeRepository.save(employee);

    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()){
            return result.get();

        }else{
            try {
                throw new ResourceNotFoundException("Employee"+id+ "id");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }




}