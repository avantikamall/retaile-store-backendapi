package com.wipro.retailStore.DTO;

import java.util.List;

import com.wipro.retailStore.entity.LineItem;

public class CartDTO {

	private String message;
	private int cartId;
	private List<LineItem> lineItem;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<LineItem> getLineItem() {
		return lineItem;
	}
	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}
	public CartDTO(String message, int cartId, List<LineItem> lineItem) {
		super();
		this.message = message;
		this.cartId = cartId;
		this.lineItem = lineItem;
	}
	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartDTO [message=" + message + ", cartId=" + cartId + ", lineItem=" + lineItem + "]";
	}
	
	
}
