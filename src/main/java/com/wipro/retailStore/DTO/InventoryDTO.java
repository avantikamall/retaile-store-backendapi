package com.wipro.retailStore.DTO;

import com.wipro.retailStore.entity.Inventory;
import com.wipro.retailStore.entity.Product;

public class InventoryDTO {

	private String message;
	private Inventory inventory;
	private Product product;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public InventoryDTO(String message, Inventory inventory, Product product) {
		super();
		this.message = message;
		this.inventory = inventory;
		this.product = product;
	}
	public InventoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
