package com.jsp.emp.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.emp.responsestructure.ResponseStructure;
import com.jsp.emp.service.exceptionclasses.InvalidCredentialException;
import com.jsp.emp.service.exceptionclasses.InvalidEmployeeIdException;
import com.jsp.emp.service.exceptionclasses.NoActiveEmployeeFoundException;
import com.jsp.emp.service.exceptionclasses.NoEmployeeFoundException;

@RestControllerAdvice
public class EmployeeExceptionHandler {
	
	@ExceptionHandler(NoActiveEmployeeFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noActiveEmployeeFoundExceptionHandler(NoActiveEmployeeFoundException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No active employee found");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEmployeeIdException.class)
	public ResponseEntity<ResponseStructure<String>> invalidEmployeeIdExceptionHandler(InvalidEmployeeIdException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid Employee Id.. Unable to find");
		structure.setBody(e.getMessage());
	 	return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ResponseStructure<String>> invalidCredentialExceptionHandler(InvalidCredentialException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid email or password.. please check it");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoEmployeeFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noEmployeeFoundExceptionHandler(NoEmployeeFoundException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid employee name... unable to find please do check");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Duplicate entry of emial or password");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
}
