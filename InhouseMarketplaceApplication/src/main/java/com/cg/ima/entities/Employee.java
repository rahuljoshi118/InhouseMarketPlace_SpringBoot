package com.cg.ima.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min = 5, max = 20, message="Employee name should contains minimum 5 and maximum 20 characters.")
	private String empName;

	@Column(name = "mobile_number")
	@Size(min = 10, max = 10, message="Mobile number must be 10 digits.")
	@Pattern(regexp = "^[6-9]{1}\\d{9}$", message="Mobile number must start from 6, 7, 8, 9.")
	private String mobileNumber;

	@Column(name = "email")
	@Email(message = "Email should be valid.")
	private String email;
	
	@Column(name = "location")
	@NotBlank(message = "Location cannot be empty.")
	private String location;
	
	
//	String name = null; (@NotNull: false / @NotEmpty: false / @NotBlank: false)
//	String name = ""; (@NotNull: true / @NotEmpty: false / @NotBlank: false)
//	String name = " "; (@NotNull: true / @NotEmpty: true / @NotBlank: false)

	
	//Bi-directional
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "employeeOrder", cascade = CascadeType.REMOVE)//using
	private List<Order> orders;


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