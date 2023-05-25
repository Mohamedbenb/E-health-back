package com.Ehealth.spring.controllers;

import com.Ehealth.spring.enume.Mainoeu;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }
    @GetMapping("/employees/uniop/{id}")
    public List<Employee> getbyuniop(@PathVariable(name="id") Long id) {

        return employeeService.getbyuni(id, true);
    }
    @GetMapping("/mainoeus")
    public ResponseEntity<List<String>> getEmployeeStatuses() {
        List<String> employeeStatuses = Arrays.stream(Mainoeu.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        return ResponseEntity.ok(employeeStatuses);
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
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee( @PathVariable long id, @RequestBody Employee employeeRequest) {

        Employee employeeResponse = employeeService.updateEmployee( id, employeeRequest);
        return ResponseEntity.ok().body(employeeResponse);

    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployee( id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
