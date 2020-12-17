package com.crud.employee.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.employee.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

}
