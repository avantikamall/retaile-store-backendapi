package com.wipro.retailStore.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
	private List<Integer> lineItemId;
	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<Integer> getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(List<Integer> lineItemId) {
		this.lineItemId = lineItemId;
	}
	public Order(int orderId, List<Integer> lineItemId) {
		super();
		this.orderId = orderId;
		this.lineItemId = lineItemId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", lineItem=" + lineItemId + "]";
	}
	
}
