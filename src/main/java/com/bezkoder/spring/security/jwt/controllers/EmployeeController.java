package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.models.Employee;
import com.bezkoder.spring.security.jwt.payload.dtos.EmployeeDto;
import com.bezkoder.spring.security.jwt.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {
    @Autowired
    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {

        return employeeService.getAllEmployees().stream().map(

                employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        // convert entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        // convert DTO to entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        Employee employee = employeeService.createEmployee(employeeRequest);

        // convert entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return new ResponseEntity<EmployeeDto>(employeeResponse, HttpStatus.CREATED);

    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {

        // convert DTO to Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        Employee employee = employeeService.updateEmployee(id, employeeRequest);

        // entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
