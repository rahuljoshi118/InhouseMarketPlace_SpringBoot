package com.cg.ima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("from Employee where employee_name like %:empName%") // JPQL -> table name=Entity class
//	List<Employee> findByName(@Param("empName") String empName);
	Optional<Employee> findByName(@Param("empName") String empName);
	
	
	@Query("from Employee where email = :empEmail")
	Employee findByEmployeeEmail(@Param("empEmail") String empEmail);

}
