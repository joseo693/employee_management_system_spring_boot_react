package com.ems.service;

import java.util.List;

import com.ems.dto.EmployeeDto;


public interface EmployeeService {

    EmployeeDto creaEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

} 