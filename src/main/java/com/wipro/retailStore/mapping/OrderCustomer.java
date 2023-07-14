package com.wipro.retailStore.mapping;

public class OrderCustomer {

	private String message;
	private long orderId;
	private long custId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public OrderCustomer(String message, long orderId, long custId) {
		super();
		this.message = message;
		this.orderId = orderId;
		this.custId = custId;
	}
	public OrderCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
