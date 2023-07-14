package com.wipro.retailStore.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
	private long custId;
	private List<Integer> lineItemId;
	
	
	

	public int getCartId() {
		return cartId;
	}




	public void setCartId(int cartId) {
		this.cartId = cartId;
	}




	public long getCustId() {
		return custId;
	}




	public void setCustId(long custId) {
		this.custId = custId;
	}




	public List<Integer> getLineItemId() {
		return lineItemId;
	}




	public void setLineItemId(List<Integer> lineItemId) {
		this.lineItemId = lineItemId;
	}




	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Cart(int cartId, long custId, List<Integer> lineItemId) {
		super();
		this.cartId = cartId;
		this.custId = custId;
		this.lineItemId = lineItemId;
	}




	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", lineItem=" + lineItemId + "]";
	}	
	
}
