package com.wipro.retailStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int custId;
	private String custName;
	private String custEmail;
	private int billingAddId;
	private int shippingAddId;
	
	
	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getCustEmail() {
		return custEmail;
	}


	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}


	public int getBillingAddId() {
		return billingAddId;
	}


	public void setBillingAddId(int billingAddId) {
		this.billingAddId = billingAddId;
	}


	public int getShippingAddId() {
		return shippingAddId;
	}


	public void setShippingAddId(int shippingAddId) {
		this.shippingAddId = shippingAddId;
	}


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Customer(int custId, String custName, String custEmail, int billingAddId, int shippingAddId) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.billingAddId = billingAddId;
		this.shippingAddId = shippingAddId;
	}
	
	
	
}
