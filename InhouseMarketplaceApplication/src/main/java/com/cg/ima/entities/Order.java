package com.cg.ima.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq")
	@SequenceGenerator(name="order_seq",sequenceName="order_seq", allocationSize=1)
	private int orderId;
	
	@Column(name = "order_date")
	private LocalDate orderDate;

	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employeeOrder;
	
	
	
	@ManyToMany
	@JoinTable(name = "resource_order", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
	private List<Resource> resources;


	public Order() {
		super();
	}

	public Order(int orderId, LocalDate orderDate, List<Resource> resources,
			Employee employeeOrder) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.resources = resources;
		this.employeeOrder = employeeOrder;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Employee getEmployeeOrder() {
		return employeeOrder;
	}

	public void setEmployeeOrder(Employee employeeOrder) {
		this.employeeOrder = employeeOrder;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", resources="
				+ resources + ", employeeOrder=" + employeeOrder + "]";
	}


	
}
