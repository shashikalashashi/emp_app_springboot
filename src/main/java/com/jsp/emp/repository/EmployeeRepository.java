package com.jsp.emp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.emp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	/*
	 * @Query("select e from Employee where e.email=:email and e.password=:password"
	 * )
	 */
	Optional<Employee> findByEmailAndPassword(String email, String password);

	List<Employee> findByName(String name);

	@Query("select e from Employee e where e.status='ACTIVE'")
	List<Employee> findAllActiveEmployees();

	
	@Query("select e from Employee e where e.status='IN_ACTIVE'")
	List<Employee> findAllInactiveEmployees();
	
}
