package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	
	
	@Override
	public Employee addEmployee(Employee emp) throws EmployeeExistsException {
		
		Employee searchedEmployee = employeeRepository.findByEmployeeEmail(emp.getEmail());

		if (searchedEmployee != null && emp.getEmail().equals(searchedEmployee.getEmail())) 
		{
			throw new EmployeeExistsException("Employee with email '"+emp.getEmail()+"' already exists!");
		}
		else
		{
			Employee employee = employeeRepository.save(emp);
			return employee;
		}

	}

	@Override
	public Employee updateEmployee(Employee emp) {
		
		Employee employee = employeeRepository.save(emp);
		return employee;
		
	}

	@Override
	public ResponseEntity<String> removeEmployee(int empId) throws EmployeeNotFoundException {
		
		Optional<Employee> searchedEmployee = employeeRepository.findById(empId);

		if (!searchedEmployee.isPresent()) 
		{
			throw new EmployeeNotFoundException("Employee ID '"+ empId +"' Doesn't Exists!");
		}
		else
		{
			employeeRepository.deleteById(empId);
			return ResponseEntity.ok("Employee Id '"+empId+"' has been deleted!");
		}
		
	}

	@Override
	public Employee getEmployeeById(int empId) throws EmployeeNotFoundException {
		
		Optional<Employee> emp = employeeRepository.findById(empId);

		if (emp.isPresent()) 
		{
			return emp.get();
		} 
		else 
		{
			throw new EmployeeNotFoundException("Employee ID '"+ empId +"' Not Found!");
		}
		
	}

//if we want all employees using like query	
//	@Override
//	public List<Employee> getAllEmployeesByName(String empName) {
//		
//		List<Employee> emp = employeeRepository.findByName(empName);
//		return emp;
//		
//	}

// if we want a single employee by their full name		
	@Override
	public Employee getEmployeeByName(String empName) throws EmployeeNotFoundException {
		
		Optional<Employee> emp = employeeRepository.findByName(empName);

		if (emp.isPresent()) 
		{
			return emp.get();
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Name '"+ empName +"' Not Found!");
		}
	}


	@Override
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) 
		{	
			throw new EmployeeNotFoundException("No Employee Found!");
		}
		else
		{	
			return employees;
		}
		
	}


}
