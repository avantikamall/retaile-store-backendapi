package com.wipro.retailStore.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Orders {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
	private long custId;
 	private List<Integer> lineItemId;
	
	
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public List<Integer> getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(List<Integer> lineItemId) {
		this.lineItemId = lineItemId;
	}
	
	public Orders(long orderId, long custId, List<Integer> lineItemId) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.lineItemId = lineItemId;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", lineItem=" + lineItemId + "]";
	}
	
}
