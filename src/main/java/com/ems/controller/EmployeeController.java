package com.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDto;
import com.ems.service.EmployeeService;

import jakarta.persistence.Converts;




//@RestController - marks this class as a RESTful web controller.
//@RequestMapping - defines a base URL for all endpoints in this controller
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // injecting EmployeeService
    private EmployeeService employeeService;

    // constructor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    // API to create a new Employee object
    //@RequestBody - automatically converts JSON data from the HTTP request body into a EmployeeDto object using Jackson
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.creaEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // API to retrieve an Employee by a given ID
    // @PathVariable - used to extract values from the URL path and bind them to method parameters.
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById( @PathVariable("id") Long employeeId ) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // API to retrieve all Employees
    // ResponseEntity<T> - wrapper that represents the entire HTTP response: The body and the status code
    @GetMapping
    public ResponseEntity< List<EmployeeDto> > getAllEmployees() {
        // employeeRepository.findAll() - returns all employees from the database
        // Converts all Employee objects into EmployeeDto objects
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    


}
