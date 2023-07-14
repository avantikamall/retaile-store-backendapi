package com.wipro.retailStore.DTO;

import com.wipro.retailStore.entity.CustomerAddress;

public class CustomerDTO {

	private String message;
	private long custId;
	private String custName;
	private String custEmail;
	private CustomerAddress billingAddress;
	private CustomerAddress shippingAddress;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public CustomerAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(CustomerAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public CustomerAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(CustomerAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public CustomerDTO(String message, long custId, String custName, String custEmail, CustomerAddress billingAddress,
			CustomerAddress shippingAddress) {
		super();
		this.message = message;
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
	}
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerDTO [message=" + message + ", custId=" + custId + ", custName=" + custName + ", custEmail="
				+ custEmail + ", billingAddress=" + billingAddress + ", shippingAddress=" + shippingAddress + "]";
	}
	
	
	
	
}
