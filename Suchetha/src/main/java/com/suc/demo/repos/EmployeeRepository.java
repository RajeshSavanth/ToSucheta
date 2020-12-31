package com.suc.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suc.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmailId(String emailId);
	
//	Employee findByEmailId(String emailId);
	
}
