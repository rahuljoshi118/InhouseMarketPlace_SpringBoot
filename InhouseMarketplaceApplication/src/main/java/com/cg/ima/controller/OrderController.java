package com.cg.ima.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Order;
import com.cg.ima.exception.OrderNotFoundException;
import com.cg.ima.service.OrderService;

@RestController
@RequestMapping("/ima")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	

// if we want to post(insert) a single order
	@PostMapping(path = "/order")
	public Order addOrder(@RequestBody Order order) {
	
		Order o = orderService.addOrder(order);		
		return o;
		
	}
	
	@PutMapping(path = "/order")
	public Order updateOrder(@RequestBody Order order) {
		
		Order o = orderService.updateOrder(order);
		return o;
		
	}

	@DeleteMapping(path = "/order/{orderId}")
	public void removeOrder(@PathVariable("orderId") int orderId) {
		
		orderService.removeOrder(orderId);

	}

	@GetMapping(path = "/order/{orderId}")
	public Order getOrderById(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
	
		Order order = orderService.getOrderById(orderId);
		// Throw user defined exception
		if (order == null) {
			OrderNotFoundException orderNotFound = new OrderNotFoundException("Order ID '"+ orderId +"' Not Found!");
			throw orderNotFound;
		}
		else
		{	
			return order;
		}
		
	}

//if we want all orders using like query	
	@GetMapping(path = "/order/date/{orderDate}")
	public List<Order> getAllOrdersByDate(@PathVariable("orderDate")@DateTimeFormat(iso = ISO.DATE) LocalDate orderDate) throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrdersByDate(orderDate);
		if (orders.isEmpty()) {
			OrderNotFoundException orderNotFound = new OrderNotFoundException("Order On Date '"+ orderDate +"' Not Found!");
			throw orderNotFound;
		}
		else
		{	
			return orders;
		}
	
	}


	@GetMapping(path = "/order")
	public List<Order> getAllOrders() throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrders();
		if (orders.isEmpty()) {
			OrderNotFoundException orderNotFound = new OrderNotFoundException("No Order Found!");
			throw orderNotFound;
		}
		else
		{	
			return orders;
		}
	}

}
