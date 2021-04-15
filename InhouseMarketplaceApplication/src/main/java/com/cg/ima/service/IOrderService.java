package com.cg.ima.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.ima.entities.Order;

public interface IOrderService {
	
	public Order addOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public void removeOrder(int orderId);
	
	public Order getOrderById(int orderId);
	
	public List<Order> getAllOrdersByDate(LocalDate orderDate);
	
	public List<Order> getAllOrders();

}
