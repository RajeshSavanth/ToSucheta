package com.suc.demo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suc.demo.entity.Employee;
import com.suc.demo.repos.EmployeeRepository;
import com.suc.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/add")
public class EmployeeController {
	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private EmployeeService emplSer;
	
	@Autowired
	private EmployeeRepository emplRepos;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createEmpl(@RequestBody Employee empl) {
		logger.info("The employee object is creating");
		return emplSer.createEmpl(empl);
	}
	
	@GetMapping("/getAll")
	public List<Employee> getAll() {
		logger.info("We are geeting all The employees");
		return emplRepos.findAll();
	}
	
	@GetMapping("/getOne/{id}")
	public Optional<Employee> getEmpById(@PathVariable Long id) {
		logger.info("We are geeting employee by id");
		return emplRepos.findById(id);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmpl(@RequestBody Employee empl) {
		logger.info("We are updating employee by id");
		Employee empls = new Employee();
		empls.setId(empl.getId());
		empls.setAddress(empl.getAddress());
		empls.setAge(empl.getAge());
		empls.setEmailId(empl.getEmailId());
		empls.setFirstName(empl.getFirstName());
		empls.setLastName(empl.getLastName());
		empls.setGender(empl.getGender());
		
		Employee saved = emplRepos.save(empls);		
		return ResponseEntity.ok(saved);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmp(@PathVariable Long id) {
		
		Employee er = emplRepos.getOne(id);
		emplRepos.delete(er);
		logger.info("We are deleting employee by id");
		return "Deleted";
	}
}
