package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ima.entities.Employee;
import com.cg.ima.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	
	
	@Override
	public Employee addEmployee(Employee emp) {
		
		Employee employee = employeeRepository.save(emp);
		return employee;

	}

	@Override
	public Employee updateEmployee(Employee emp) {
		
		Employee employee = employeeRepository.save(emp);
		return employee;
		
	}

	@Override
	public void removeEmployee(int empId) {
		
		employeeRepository.deleteById(empId);
		
	}

	@Override
	public Employee getEmployeeById(int empId) {
		
		Optional<Employee> emp = employeeRepository.findById(empId);

		if (emp.isPresent()) 
		{
			return emp.get();
		} 
		else 
		{
			return null;
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
	public Employee getEmployeeByName(String empName) {
		
		Optional<Employee> emp = employeeRepository.findByName(empName);

		if (emp.isPresent()) {
			return emp.get();
		}

		return null;
		
	}


	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
		
	}


}
