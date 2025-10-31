package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.entity.Employee;

// JpaRepository provides CRUD (Create, Read, Update, Delete) operations for entity class automatically.
// first parameter: entity Type, Second parameter: type of the primary key
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
