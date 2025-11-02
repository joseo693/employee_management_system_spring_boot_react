package com.ems.mapper;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;


// The mapper class converts between Entities and DTOs, In this case from Employee to EmployeeDto and vice versa.
public class EmployeeMapper {
    
    // Converts Entity object to Dto object
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail()
        );
    }

    // Converts Dto object to Entity Object
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail()
        );
    }

    
}
