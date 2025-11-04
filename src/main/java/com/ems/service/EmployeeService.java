package com.ems.service;

import com.ems.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto creaEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

} 