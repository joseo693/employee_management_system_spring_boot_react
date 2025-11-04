package com.ems.service.impl;

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
    public EmployeeDto creaEmployee(EmployeeDto employeeDto) {

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
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id : " + employeeId) 
            );
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    
}
