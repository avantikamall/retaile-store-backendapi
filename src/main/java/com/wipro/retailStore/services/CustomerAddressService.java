package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.CustomerAddress;
import com.wipro.retailStore.repo.CustomerAddressRepository;

@Service
public class CustomerAddressService {

	@Autowired
	CustomerAddressRepository custAddrepo;
	
	public CustomerAddress addAddress(CustomerAddress address) {
		
		return custAddrepo.save(address);
		
	}
	
	public CustomerAddress getAddressById (int addressId) {
		CustomerAddress c = new CustomerAddress(0,null,null,null,null,0);
		return custAddrepo.findById(addressId).isPresent()?custAddrepo.findById(addressId).get():c;
	}
	
	public List<CustomerAddress> getAllAddress(){
		List<CustomerAddress> address = new ArrayList<>();
		custAddrepo.findAll().forEach(ads->address.add(ads));
		return address;
	}
	
	public String deleteAddress(int addressId) {
		CustomerAddress c = getAddressById(addressId);
		if(c.getAddressId()!=0) {		
		custAddrepo.deleteById(addressId);
		return "Address Deleted Successfully!";
		}else {
			return "Address doesn't exist!";
		}
	}
	
	public CustomerAddress updateAddress(int addressId, CustomerAddress ca) {
		CustomerAddress c = custAddrepo.findById(addressId).get();
		CustomerAddress newaddress = new CustomerAddress();
		
		newaddress.setAddressId(addressId);
		if(ca.getCity()==null) {
			newaddress.setCity(c.getCity());
		}
		else{
			newaddress.setCity(ca.getCity());
		}
		if(ca.getDoorNo()==null) {
			newaddress.setDoorNo(c.getDoorNo());
		}else {
			newaddress.setDoorNo(ca.getDoorNo());
		}
		if(ca.getLayout()==null) {
			newaddress.setLayout(c.getLayout());
		}else {
			newaddress.setLayout(ca.getLayout());
		}
		if(ca.getStreetName()==null) {
			newaddress.setStreetName(c.getStreetName());
		}else {
			newaddress.setStreetName(ca.getStreetName());
		}
		if(ca.getPincode()<=0) {
			newaddress.setPincode(c.getPincode());
		}else {
			newaddress.setPincode(ca.getPincode());
		}
		
		
		return custAddrepo.save(newaddress);
	}
	
	
}
