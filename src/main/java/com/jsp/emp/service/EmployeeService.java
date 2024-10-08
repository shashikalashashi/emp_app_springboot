package com.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jsp.emp.dao.EmployeeDao;
import com.jsp.emp.entity.Employee;
import com.jsp.emp.responsestructure.ResponseStructure;
import com.jsp.emp.service.exceptionclasses.InvalidCredentialException;
import com.jsp.emp.service.exceptionclasses.InvalidEmployeeIdException;
import com.jsp.emp.service.exceptionclasses.NoActiveEmployeeFoundException;
import com.jsp.emp.service.exceptionclasses.NoEmployeeFoundException;
import com.jsp.emp.service.exceptionclasses.NoInActiveEmployeeFoundException;
import com.jsp.emp.util.EmployeeStatus;

@Component
public class EmployeeService {
	
	@Autowired
	private EmployeeDao dao;
	
	
	public ResponseEntity<?> saveEmployee(Employee employee)
	{
		employee.setStatus(EmployeeStatus.IN_ACTIVE);
	return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Employee Saved successfully..").body(dao.saveEmployee(employee)).build());
	}
	
	
	public ResponseEntity<?> updateEmployee(Employee employee)
	{
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee update successfully..").body(dao.updateEmployee(employee)).build());
	}
	
	public ResponseEntity<?> findEmployeeByID(int id)
	{
		Optional<Employee> optional=dao.findEmployeeById(id);
		if(optional.isEmpty())
		{
			throw InvalidEmployeeIdException.builder().message("Invalid Employee id... Unable to find").build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee found successfully...").body(optional.get()).build());
		}
	}
	
	public ResponseEntity<?> findAllEmployees()
	{
		List<Employee> employees=dao.findAllActiveEmployees();
		if(employees.isEmpty())
		{
			throw NoActiveEmployeeFoundException.builder().message("No Active Employees Found").build();
		}
		
		
//		ArrayList<Employee> activeEmployees=new ArrayList<>();
//		
//		for(Employee e:employees)
//		{
//			if(e.getStatus()==EmployeeStatus.ACTIVE)
//			
//				activeEmployees.add(e);
//		}

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All Employees found successfully...").body(employees).build());
	
	}
	
	
	 public ResponseEntity<?> findAllInactiveEmployees() 
	 { 
		 List<Employee> employees=dao.findAllInactiveEmployees();
		 if(employees.isEmpty())
		 {
			 throw NoInActiveEmployeeFoundException.builder().message("No In_Active Employees Found").build();
		 }
			return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All Inactive Employees found successfully...").body(employees).build()); 
	 }
	 
	
	public ResponseEntity<?> deleteEmployeeById(int id)
	{
		Optional<Employee> optional=dao.findEmployeeById(id);
		if(optional.isEmpty())
		{
			throw InvalidEmployeeIdException.builder().message("Invalid Employee Id... Unable to delete employee..").build();
		}
		else
		{
			dao.deleteEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee deleted successfully..").body("employee deleted").build());
		}
		
	}
	
	public ResponseEntity<?> findEmployeeByEmailAndPassword(String email, String password)
	{
		Optional<Employee> optional=dao.findEmployeeByEmailAndPassword(email, password);
		if(optional.isEmpty())
		{
			throw InvalidCredentialException.builder().message("Invalid credentials unable to find").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("verification successfull..").body(optional.get()).build());
	}
	
	public ResponseEntity<?> findEmployeeByName(String name)
	{
		List<Employee> employees=dao.findEmployeeByName(name);
		if( employees.isEmpty())
		{
			throw NoEmployeeFoundException.builder().message("No Mtachimg Employees found For The Requested Name").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee found successfully for the passsed name").body(dao.findEmployeeByName(name)).build());
	}


	public ResponseEntity<?> setEmployeeStatusToActive(int id) 
	{
		Optional<Employee> emOptional=dao.findEmployeeById(id);
		if(emOptional.isEmpty())
		{
			throw InvalidEmployeeIdException.builder().message("Invalid Employee id... Unable to set status to active").build();
		}
		Employee employee=emOptional.get();
		employee.setStatus(EmployeeStatus.ACTIVE);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee Status Updated To Avctive").body(dao.updateEmployee(employee)).build());
	}


	public ResponseEntity<?> setEmployeeStatusToInActive(int id) {
		Optional<Employee> emOptional=dao.findEmployeeById(id);
		if(emOptional.isEmpty())
		{
			throw InvalidEmployeeIdException.builder().message("Invalid Employee id... Unable to set status to In_active").build();
		}
		Employee employee=emOptional.get();
		employee.setStatus(EmployeeStatus.IN_ACTIVE);
		return  ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee Status Updated To In_Avctive").body(dao.updateEmployee(employee)).build());
	}
}

