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
	private List<Integer> lineItemId;
	
	
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<Integer> getLineItem() {
		return lineItemId;
	}
	public void setLineItem(List<Integer> lineItemId) {
		this.lineItemId = lineItemId;
	}
	public Cart(int cartId, List<Integer> lineItemId) {
		super();
		this.cartId = cartId;
		this.lineItemId = lineItemId;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", lineItem=" + lineItemId + "]";
	}	
	
}
