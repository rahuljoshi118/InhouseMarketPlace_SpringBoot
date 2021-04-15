package com.cg.ima.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name = "employee_seq",sequenceName = "employee_seq", allocationSize=1)
	private int empId;

	@Column(name = "employee_name")
	private String empName;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "email")
	private String email;
	
	@Column(name = "location")
	private String location;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeOrder")
	private List<Order> orders = new ArrayList<Order>();


	public Employee() {
		super();
	}

	public Employee(int empId, String empName, String mobileNumber, String email, String location, List<Order> orders,
			Resource resource) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.location = location;
		this.orders = orders;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", mobileNumber=" + mobileNumber + ", email="
				+ email + ", location=" + location + ", orders=" + orders + "]";
	}
	
	
	
}

//
//{
//    "empName":"Rohit",
//    "mobileNumber":"123456789",
//    "email":"rohithayaran@gmail.com",
//    "location":"mumbai"
//}
