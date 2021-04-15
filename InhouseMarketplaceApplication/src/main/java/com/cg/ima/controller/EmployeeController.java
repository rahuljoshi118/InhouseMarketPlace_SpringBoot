package com.cg.ima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.service.EmployeeService;

@RestController
@RequestMapping("/ima")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	
// if we want to post(insert) multiple employees	
//	@PostMapping(path = "/employee")
//	public List<Employee> addEmployee(@RequestBody List<Employee> employees) {
//		
//		for(Employee e: employees)
//		{
//			Employee emp = employeeService.addEmployee(e);
//		}		
//		return employees;
//		
//	}

// if we want to post(insert) a single employee
	@PostMapping(path = "/employee")
	public Employee addEmployee(@RequestBody Employee emp) {
	
		Employee employee = employeeService.addEmployee(emp);		
		return employee;
		
	}
	
	@PutMapping(path = "/employee")
	public Employee updateEmployee(@RequestBody Employee emp) {
		
		Employee employee = employeeService.updateEmployee(emp);
		return employee;
		
	}

	@DeleteMapping(path = "/employee/{empId}")
	public void removeEmployee(@PathVariable("empId") int empId) {
		
		employeeService.removeEmployee(empId);

	}

	@GetMapping(path = "/employee/{empId}")
	public Employee getEmployeeById(@PathVariable("empId") int empId) throws EmployeeNotFoundException {
	
		Employee employee = employeeService.getEmployeeById(empId);
		// Throw user defined exception
		if (employee == null) {
			EmployeeNotFoundException employeeNotFound = new EmployeeNotFoundException("Employee ID '"+ empId +"' Not Found!");
			throw employeeNotFound;
		}
		else
		{	
			return employee;
		}
	}

//if we want all employees using like query	
//	@GetMapping(path = "/employee/name/{empName}")
//	public List<Employee> getAllEmployeesByName(@PathVariable("empName") String empName) {
//		
//		List<Employee> employees = employeeService.getAllEmployeesByName(empName);
//		return employees;
//	
//	}

// if we want a single employee by their full name	
	@GetMapping(path = "/employee/name/{empName}")
	public Employee getEmployeeByName(@PathVariable("empName") String empName) throws EmployeeNotFoundException {
		
		Employee employee = employeeService.getEmployeeByName(empName);
		// Throw user defined exception
		if (employee == null) {
			EmployeeNotFoundException employeeNotFound = new EmployeeNotFoundException("Employee Name '"+ empName +"' Not Found!");
			throw employeeNotFound;
		}
		else
		{	
			return employee;
		}
	
	}

	@GetMapping(path = "/employee")
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		
		List<Employee> employees = employeeService.getAllEmployees();
		if (employees.isEmpty()) {
			EmployeeNotFoundException employeeNotFound = new EmployeeNotFoundException("No Employee Found!");
			throw employeeNotFound;
		}
		else
		{	
			return employees;
		}
	}

}
