package com.suc.demo.service;

import javax.persistence.Transient;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.suc.demo.entity.Employee;
import com.suc.demo.repos.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository emplRepos;
	
	@Transient
	public ResponseEntity<Object> createEmpl(Employee empl) {
		Employee emp = new Employee();
		if (emplRepos.findByEmailId(emp.getEmailId()) !=null ) {
			return ResponseEntity.badRequest().body("Employee with this email is already present");
		} else {
		emp.setId(empl.getId());
		emp.setFirstName(empl.getFirstName());
		emp.setLastName(empl.getLastName());
//		emp.setLastName(empl.getLastName());
		emp.setAddress(empl.getAddress());
		emp.setAge(empl.getAge());
		emp.setEmailId(empl.getEmailId());
		emp.setGender(empl.getGender());
		
		Employee savedEmpl = emplRepos.save(emp);
		if(emplRepos.findById(savedEmpl.getId()).isPresent())
			return ResponseEntity.ok("Employee got created");
		else return ResponseEntity.unprocessableEntity().body("Failed to create employee");
		}
	}
}
