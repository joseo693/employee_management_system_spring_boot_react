package com.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.mapper.EmployeeMapper;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Injecting EmployeeRepository
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // overriding the creaEmployee() from the EmployeeService.java class
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // converting EmployeeDto object to Employee
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // Need to save new Employee object to the Database
        Employee savedEmployee = employeeRepository.save(employee);

        // converting savedEmployee object to EmployeeDto object
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // overriding the getEmployeeById() from the EmployeeService.java class
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        // employeeRepository extends JpaRepository, which contains a findById()
        // .orElseThrow() - If the employee ID doesn’t exist in the database, this throws a custom exception
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id : " + employeeId) 
            );
        return EmployeeMapper.mapToEmployeeDto(employee);
    }


    // overriding the getAllEmployees() from the EmployeeService.java class
    @Override
    public List<EmployeeDto> getAllEmployees() {
        // Getting a list of all of the employees
        List<Employee> employees = employeeRepository.findAll();

        // converting from Employee object to EmployeeDto object
        // .stream - turns the list into a pipeline of elements that can be processed
        // .map() - transforms each item in the stream into something else
        // .collect() - gathers the results of the stream into a List
        // Collectors.toList() - collects all the transformed DTOs into a list
        return employees.stream()
            .map( (employee) -> EmployeeMapper.mapToEmployeeDto(employee))
            .collect( Collectors.toList() );
    }

    // overriding the updateEmployeeById() from the EmployeeService.java class
    @Override
    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeToUpdate) {
        // employeeRepository extends JpaRepository, which contains a findById()
        // .orElseThrow() - If the employee ID doesn’t exist in the database, this throws a custom exception
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id : " + employeeId)
        );

        // updating the necessary properties from employeeToUpdate(EmployeeDto) into new Employee object
        employee.setFirstName(employeeToUpdate.getFirstName());
        employee.setLastName(employeeToUpdate.getLastName());
        employee.setEmail(employeeToUpdate.getEmail());

        // saving the new Employee object 
        Employee  updatedEmployee = employeeRepository.save(employee);

        // Converting updatedEmployee(EmployeeDto)  object to EmployeeDto
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    // overriding the deleteEmployeeById() from the EmployeeService.java class
    @Override
    public void deleteEmployeeById(Long employeeId) {
        // employeeRepository extends JpaRepository, which contains a findById()
        // .orElseThrow() - If the employee ID doesn’t exist in the database, this throws a custom exception
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id : " + employeeId)
        );

        // Delete Emplyee object with given id
        employeeRepository.deleteById(employeeId);
    }

    
}
