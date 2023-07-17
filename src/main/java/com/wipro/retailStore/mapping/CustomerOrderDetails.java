package com.wipro.retailStore.mapping;

import com.wipro.retailStore.DTO.CustomerDTO;
import com.wipro.retailStore.DTO.OrderDTO;

public class CustomerOrderDetails {

	private String message;
	private CustomerDTO customer;
	private OrderDTO order;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	public CustomerOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerOrderDetails(String message, CustomerDTO customer, OrderDTO order) {
		super();
		this.message = message;
		this.customer = customer;
		this.order = order;
	}
	
	
}
