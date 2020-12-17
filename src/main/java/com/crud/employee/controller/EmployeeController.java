package com.crud.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.employee.exception.ResourceNotFoundException;
import com.crud.employee.model.Employee;
import com.crud.employee.repositery.EmployeeRepo;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepo.findAll();
		
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
		
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not exist with id =" + id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee em){
		
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not exist with id =" + id));
		employee.setFirstName(em.getFirstName());
		employee.setLastName(em.getLastName());
		employee.setEmailId(em.getEmailId());
		
		Employee updatedEmployee = employeeRepo.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not exist with id =" + id));//		employeeRepo.deleteById(id);
		employeeRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	
	}

	
}
