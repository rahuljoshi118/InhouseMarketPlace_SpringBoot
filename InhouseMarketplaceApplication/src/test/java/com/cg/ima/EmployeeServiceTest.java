package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.repository.IEmployeeRepository;
import com.cg.ima.service.EmployeeService;



@ExtendWith(MockitoExtension.class)//Junit 5
public class EmployeeServiceTest {

	@Mock
	IEmployeeRepository employeeRepository;
	
	
	@InjectMocks
	EmployeeService employeeService;
	
	
	@Test
	public void testAddEmployee() throws EmployeeExistsException
	{
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		assertEquals(emp, employeeService.addEmployee(emp));
	}
	
	@Test
	public void testUpdateEmployee()
	{
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		emp.setEmail("rahuljoshi@gmail.com");
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		
		assertEquals(emp,employeeService.updateEmployee(emp));
		
	}
	
	@Test
	public void testDeleteEmployee()
	{
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		Mockito.when(employeeRepository.existsById(emp.getEmpId())).thenReturn(false);
	    assertFalse(employeeRepository.existsById(emp.getEmpId()));
	}
	
	
	@Test
	public void testGetEmployeeById() throws EmployeeNotFoundException
	{
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		Mockito.when(employeeRepository.findById(10)).thenReturn(Optional.of(emp));
		Employee actualEmp = employeeService.getEmployeeById(10);
		assertEquals(10,actualEmp.getEmpId());
	}

	
	@Test
	public void testGetEmployeeByName() throws EmployeeNotFoundException
	{
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		Mockito.when(employeeRepository.findByName("Chandan Hayaran")).thenReturn(Optional.of(emp));
		Employee actualEmployee = employeeService.getEmployeeByName("Chandan Hayaran");
		assertEquals("Chandan Hayaran",actualEmployee.getEmpName());
	}
	

	@Test
	public void testGetAllEmployees()
	{
		List<Employee> employees = new ArrayList<>();
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		Exception ex = assertThrows(EmployeeNotFoundException.class, ()-> employeeService.getAllEmployees());//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Employee Found!", ex.getMessage());
		
	}
}