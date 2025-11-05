package com.ems.service;

import java.util.List;

import com.ems.dto.EmployeeDto;


public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeToUpdate);

    void deleteEmployeeById(Long employeeId);

} 