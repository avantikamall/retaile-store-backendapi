package com.wipro.retailStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lineItem")
public class LineItem {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LineItem(int itemId, int productId, String productName, int quantity, double price) {
		super();
		this.itemId = itemId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LineItem [itemId=" + itemId + ", productId=" + productId + ", productName=" + productName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
	
	
}
