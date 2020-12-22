package com.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cs.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "SELECT sum(salary) FROM Employee")
    public Long sumSalary();
	
	

}
