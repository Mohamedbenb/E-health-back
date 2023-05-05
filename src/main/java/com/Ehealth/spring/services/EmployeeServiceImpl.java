package com.Ehealth.spring.services;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.repository.UniOpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAllByActive(true);
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


                    employee.setFirstname(employeeRequest.getFirstname());
                    employee.setLastname(employeeRequest.getLastname());
                    employee.setMatricule(employeeRequest.getMatricule());
                    employee.setDaterecru(employeeRequest.getDaterecru());
                    employee.setDatenai(employeeRequest.getDatenai());
                    employee.setPostetrav(employeeRequest.getPostetrav());
                    employee.setEmail(employeeRequest.getEmail());
            if (employeeRequest.getDatenai()!=null){
                employee.setAge(YEARS.between(employeeRequest.getDatenai(), LocalDate.now()));
            }else {
                employee.setAge(null);
            }
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