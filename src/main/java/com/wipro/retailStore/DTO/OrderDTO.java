package com.wipro.retailStore.DTO;

import java.util.List;

import com.wipro.retailStore.entity.LineItem;

public class OrderDTO {

	private String message;
	private long orderId;
	private List<LineItem> lineItem;
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
	public List<LineItem> getLineItem() {
		return lineItem;
	}
	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}
	public OrderDTO(String message, long orderId, List<LineItem> lineItem) {
		super();
		this.message = message;
		this.orderId = orderId;
		this.lineItem = lineItem;
	}
	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderDTO [message=" + message + ", orderId=" + orderId + ", lineItem=" + lineItem + "]";
	}
	
	
	
}
