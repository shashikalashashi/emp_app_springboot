package com.jsp.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.emp.dao.EmployeeDao;
import com.jsp.emp.entity.Employee;
import com.jsp.emp.repository.EmployeeRepository;
import com.jsp.emp.responsestructure.ResponseStructure;
import com.jsp.emp.service.EmployeeService;

@RequestMapping("/employees")
@RestController
public class EmployeeController {
	
	@Autowired
	private  EmployeeService es;
	
	@PostMapping
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee)
	{
		return es.saveEmployee(employee);
	}
	
	@PutMapping
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
	{
		return es.updateEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findEmployeeById(@PathVariable int id)
	{
		return es.findEmployeeByID(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllEmployees()
	{
		return es.findAllEmployees();
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<?> deleteEmployeeById(@PathVariable int id)
	{
		return es.deleteEmployeeById(id);
	}
	
	@GetMapping("/{email}/{password}")
	public ResponseEntity<?> findEmployeeByEmailAndPassword(@PathVariable String email, @PathVariable String password)
	{
		return es.findEmployeeByEmailAndPassword(email, password);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findEmployeeByName(@PathVariable String name)
	{
		return es.findEmployeeByName(name);
	}
	
	@PatchMapping("/active/{id}")
	public  ResponseEntity<?> setEmployeeStatusToActive(@PathVariable int id)
	{
		return es.setEmployeeStatusToActive(id);
	}
	
	@PatchMapping("/inactive/{id}")
	public  ResponseEntity<?> setEmployeeStatusToInActive(@PathVariable int id)
	{
		return es.setEmployeeStatusToInActive(id);
	}
	
	@GetMapping("/inactive")
	public ResponseEntity<?> findAllInactiveEmployees()
	{
		return es.findAllInactiveEmployees();
	}
}
