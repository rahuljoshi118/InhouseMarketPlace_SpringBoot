package com.cg.ima.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ima.entities.Order;
import com.cg.ima.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	
	
	@Override
	public Order addOrder(Order order) {
		
		Order o = orderRepository.save(order);
		return o;

	}

	@Override
	public Order updateOrder(Order order) {
		
		Order o = orderRepository.save(order);
		return o;
		
	}

	@Override
	public void removeOrder(int orderId) {
		
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public Order getOrderById(int orderId) {
		
		Optional<Order> order = orderRepository.findById(orderId);

		if (order.isPresent()) 
		{
			return order.get();
		} 
		else 
		{
			return null;
		}
		
	}

//if we want all orders using like query	
	@Override
	public List<Order> getAllOrdersByDate(LocalDate orderDate) {
		
		List<Order> order = orderRepository.findByDate(orderDate);
		return order;
		
	}



	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
		
	}


}
