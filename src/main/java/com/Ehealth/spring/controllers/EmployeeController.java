package com.Ehealth.spring.controllers;

import com.Ehealth.spring.exception.ResourceNotFoundException;
import com.Ehealth.spring.models.Employee;
import com.Ehealth.spring.repository.EmployeeRepository;
import com.Ehealth.spring.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

@RestController
@RequestMapping(path="/api")

public class EmployeeController {
    @Autowired
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeRepository employeeRepository){
        super();
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/{uniopId}/")
    public List<Employee> getAllEmployees(@PathVariable (value = "uniopId") Long uniopId) {

        return employeeService.getAllEmployees(uniopId);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") long id) {
        Employee employeeResponse = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id " + id + "not found"));

        return ResponseEntity.ok().body(employeeResponse);
    }

    @PostMapping("/employees/{uniopId}/")
    public ResponseEntity<Employee> createEmployee(@PathVariable(value = "uniopId") Long uniopId, @RequestBody Employee employeeRequest) {

        // convert DTO to entity


        Employee employeeResponse = employeeService.createEmployee(uniopId,employeeRequest);

        System.out.println(YEARS.between(LocalDate.now(), employeeResponse.getDatenai()));
        System.out.println(employeeResponse.getAge());


        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/employees/{uniopId}/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "uniopId") Long uniopId, @PathVariable long id, @RequestBody Employee employeeRequest) {

        // convert DTO to Entity


        Employee employeeResponse = employeeService.updateEmployee(uniopId, id, employeeRequest);


        return ResponseEntity.ok().body(employeeResponse);
    }

    @PatchMapping("/employees/{uniopId}/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "uniopId") Long uniopId, @PathVariable(value = "id") Long id) {
        employeeService.deleteEmployee(uniopId, id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}