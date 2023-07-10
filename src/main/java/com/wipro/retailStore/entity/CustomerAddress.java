package com.wipro.retailStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customerAddress")
public class CustomerAddress {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
	private String doorNo;
	private String streetName;
	private String layout;
	private String city;
	private int pincode;
	
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public CustomerAddress(int addressId, String doorNo, String streetName, String layout, String city, int pincode) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.layout = layout;
		this.city = city;
		this.pincode = pincode;
	}
	public CustomerAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerAddress [addressId=" + addressId + ", doorNo=" + doorNo + ", streetName=" + streetName
				+ ", layout=" + layout + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	
}
