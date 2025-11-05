package com.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDto;
import com.ems.service.EmployeeService;




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
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
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
    
    // API to Update Employee by Id
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable("id") Long employeeId, 
                                                          @RequestBody EmployeeDto employeeToUpdate) {
        EmployeeDto employeeDto = employeeService.updateEmployeeById(employeeId, employeeToUpdate);
        return ResponseEntity.ok(employeeDto);
    }

    // API to Delete Employee with given Id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById( @PathVariable("id") Long employeeId) {

        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Successfully deleted Employee with id : " + employeeId + "!");
    }


}
