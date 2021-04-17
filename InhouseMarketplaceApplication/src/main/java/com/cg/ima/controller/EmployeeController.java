package com.cg.ima.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Employee;
import com.cg.ima.entities.Order;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.exception.InvalidInputException;
import com.cg.ima.exception.OrderNotFoundException;
import com.cg.ima.service.EmployeeService;
import com.cg.ima.service.OrderService;

@RestController
@RequestMapping("/ima")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OrderService orderService;

	
// if we want to post(insert) multiple employees	
//	@PostMapping(path = "/employee")
//	public List<Employee> addEmployee(@Valid @RequestBody List<Employee> employees) {
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
	public Employee addEmployee(@Valid @RequestBody Employee emp, BindingResult bindingResult) throws InvalidInputException, EmployeeExistsException {
	
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Employee employee = employeeService.addEmployee(emp);
		return employee;
		
	}

	
	@PutMapping(path = "/employee")
	public Employee updateEmployee(@Valid @RequestBody Employee emp, BindingResult bindingResult) throws InvalidInputException {
		
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Employee employee = employeeService.updateEmployee(emp);
		return employee;
	}

	@DeleteMapping(path = "/employee/{empId}")
	public ResponseEntity<String> removeEmployee(@PathVariable("empId") int empId) throws EmployeeNotFoundException {
		
		return employeeService.removeEmployee(empId);

	}

	@GetMapping(path = "/employee/{empId}")
	public Employee getEmployeeById(@PathVariable("empId") int empId) throws EmployeeNotFoundException{
	
		Employee employee = employeeService.getEmployeeById(empId);
		return employee;
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
		return employee;
	}

	@GetMapping(path = "/employee")
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		
		List<Employee> employees = employeeService.getAllEmployees();
		return employees;
	}
	
	
	
	
	
//Order Controller Start	
	
	@PostMapping(path = "/employee/order")
	public Order addOrder(@RequestBody Order order) {
	
		Order o = orderService.addOrder(order);		
		return o;
		
	}
	
	
//	@PutMapping(path = "employee/order")
//	public Order updateOrder(@RequestBody Order order) {
//		
//		Order o = orderService.updateOrder(order);
//		return o;
//		
//	}
	
	@DeleteMapping(path = "/order/{orderId}")
	public ResponseEntity<String> removeOrder(@PathVariable("orderId") int orderId) {
		System.out.println("removeOrder controller");
		return orderService.removeOrder(orderId);

	}
	
	@GetMapping(path = "employee/order/{orderId}")
	public Order getOrderById(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
	
		Order order = orderService.getOrderById(orderId);
		return order;
	}

//if we want all orders using like query	
	@GetMapping(path = "employee/order/date/{orderDate}")
	public List<Order> getAllOrdersByDate(@PathVariable("orderDate")@DateTimeFormat(iso = ISO.DATE) LocalDate orderDate) throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrdersByDate(orderDate);
		return orders;
	
	}
	
//	@GetMapping(path = "employee/order")
//	public List<Order> getAllOrders() throws OrderNotFoundException {
//		
//		List<Order> orders = orderService.getAllOrders();
//		if (orders.isEmpty()) 
//		{
//			throw new OrderNotFoundException("No Order Found!");
//		}
//		else
//		{	
//			return orders;
//		}
//	}
	
	@GetMapping(path = "employee/order/empid/{empId}")
	public List<Order> getAllOrdersByEmployeeId(@PathVariable("empId") int empId) throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrdersByEmployeeId(empId);
		return orders;
	}

	
	
//Order Controller End	
}