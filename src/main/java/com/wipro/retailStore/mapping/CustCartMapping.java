package com.wipro.retailStore.mapping;

public class CustCartMapping {

	private String message;
	private long custId;
	private int cartId;
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustCartMapping(String message, long custId, int cartId) {
		super();
		this.message = message;
		this.custId = custId;
		this.cartId = cartId;
	}
	public CustCartMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
