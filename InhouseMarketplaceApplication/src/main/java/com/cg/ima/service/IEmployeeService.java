package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entities.Employee;

public interface IEmployeeService {
	
	public Employee addEmployee(Employee emp);
	
	public Employee updateEmployee(Employee emp);
	
	public void removeEmployee(int empId);
	
	public Employee getEmployeeById(int empId);
	
	public List<Employee> getAllEmployees();

//	public List<Employee> getAllEmployeesByName(String empName);
	public Employee getEmployeeByName(String empName);

}
