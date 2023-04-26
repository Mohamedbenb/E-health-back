package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.exception.ResourceNotFoundException;
import com.bezkoder.spring.security.jwt.models.Employee;
import com.bezkoder.spring.security.jwt.models.UniOp;
import com.bezkoder.spring.security.jwt.repository.EmployeeRepository;
import com.bezkoder.spring.security.jwt.repository.UniOpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UniOpRepository uniOpRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               UniOpRepository uniOpRepository) {
        super();
        this.employeeRepository = employeeRepository;
        this.uniOpRepository = uniOpRepository;
    }
    @Override
    public List<Employee> getAllEmployees(Long uniopId){

        return employeeRepository.findByUniopIdAndActive(uniopId, true);
    }
    @Override
    public Employee createEmployee(Long uniopId, Employee employee){

        return uniOpRepository.findById(uniopId).map(uniop -> {
            employee.setUniop(uniop);
            employee.setAge(YEARS.between(employee.getDatenai(), LocalDate.now()));
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("uniopId " + uniopId + " not found"));
    }
    @Override
    public Employee updateEmployee(Long uniopId, long id, Employee employeeRequest) {

        if (!uniOpRepository.existsById(uniopId)) {
            throw new ResourceNotFoundException("uniopId " + uniopId + " not found");
        }

        return employeeRepository.findById(id).map(employee -> {
                    LocalDate curDate = LocalDate.now();
                    Period period = Period.between(employeeRequest.getDatenai(), curDate);

                    employee.setFirstname(employeeRequest.getFirstname());
                    employee.setLastname(employeeRequest.getLastname());
                    employee.setMatricule(employeeRequest.getMatricule());
                    employee.setDaterecru(employeeRequest.getDaterecru());
                    employee.setDatenai(employeeRequest.getDatenai());
                    employee.setPostetrav(employeeRequest.getPostetrav());
                    employee.setEmail(employeeRequest.getEmail());
                    employee.setAge(YEARS.between(employeeRequest.getDatenai(), LocalDate.now()));
                    employee.setNumdosmed(employeeRequest.getNumdosmed());
                    employee.setStatus(employeeRequest.isStatus());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee" + id + "id"));
    }


    @Override
    public Employee deleteEmployee(Long uniopId, long id) {
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
    public Employee getEmployeeById(Long uniopId, long id) {
        Optional<Employee> result = employeeRepository.findByIdAndUniopId(uniopId,id);
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