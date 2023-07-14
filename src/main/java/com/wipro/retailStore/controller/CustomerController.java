package com.wipro.retailStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.retailStore.DTO.CustomerDTO;
import com.wipro.retailStore.entity.Customer;
import com.wipro.retailStore.entity.CustomerAddress;
import com.wipro.retailStore.services.CustomerAddressService;
import com.wipro.retailStore.services.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	CustomerService custservice;
	
	@Autowired 
	CustomerAddressService custaddservice;
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO cust) {
		try {
			CustomerDTO result = new CustomerDTO();
			if(	cust.getBillingAddress().getCity().equalsIgnoreCase(cust.getShippingAddress().getCity())&& 
					cust.getBillingAddress().getDoorNo().equalsIgnoreCase(cust.getShippingAddress().getDoorNo()) &&
					cust.getBillingAddress().getLayout().equalsIgnoreCase(cust.getShippingAddress().getLayout()) &&
					cust.getBillingAddress().getStreetName().equalsIgnoreCase(cust.getShippingAddress().getStreetName()) &&
					cust.getBillingAddress().getPincode() ==cust.getShippingAddress().getPincode()
					)
			{

				CustomerAddress sa = custaddservice.addAddress(cust.getShippingAddress());
				Customer nc = new Customer(cust.getCustId(),cust.getCustName(),cust.getCustEmail(),sa.getAddressId(),sa.getAddressId());
				Customer c = custservice.addCustomer(nc);
				
				result = new CustomerDTO("Customer Added Successfully",c.getCustId(),c.getCustName(),c.getCustEmail(),sa,sa);
			}
			else {
			
			CustomerAddress ba = custaddservice.addAddress(cust.getBillingAddress());
			CustomerAddress sa = custaddservice.addAddress(cust.getShippingAddress());
			Customer nc = new Customer(cust.getCustId(),cust.getCustName(),cust.getCustEmail(),ba.getAddressId(),sa.getAddressId());
			Customer c = custservice.addCustomer(nc);
			
			result = new CustomerDTO("Customer Added Successfully",c.getCustId(),c.getCustName(),c.getCustEmail(),ba,sa);
			}			
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch (Exception e) {
			CustomerDTO exc = new CustomerDTO() ;
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	

	@GetMapping("/customer/{cId}")
	public ResponseEntity<CustomerDTO> searchCustomer(@PathVariable("cId") long cId) {
		try {
			CustomerDTO result = new CustomerDTO();
			if(custservice.getCustById(cId)==null) {
				result.setMessage("Customer doesn't exist with given id");
				System.out.println("Not exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}else {
				Customer customer = custservice.getCustById(cId);
				CustomerAddress ba = custaddservice.getAddressById(customer.getBillingAddId());
				CustomerAddress sa = custaddservice.getAddressById(customer.getShippingAddId());
				
				result = new CustomerDTO("Success!",customer.getCustId(),customer.getCustName(),customer.getCustEmail(),ba,sa);
				
				return ResponseEntity.status(200).body(result);
			}
		}catch(Exception e) {
			CustomerDTO exc = new CustomerDTO() ;
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
		
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") long cId, @RequestBody Customer cust){
		try {
			CustomerDTO result;
			if(custservice.getCustById(cId)!=null) {
				Customer c = new Customer(cId,cust.getCustName(),cust.getCustEmail(),cust.getBillingAddId(),cust.getShippingAddId());
				Customer updated = custservice.updateCostomer(c);
				CustomerAddress ba = custaddservice.getAddressById(c.getBillingAddId());
				CustomerAddress sa = custaddservice.getAddressById(c.getShippingAddId());
				result =new CustomerDTO("Updated Successfully",updated.getCustId(),updated.getCustName(),updated.getCustEmail(),ba,sa);
				return ResponseEntity.status(200).body(result);
			
			}else {
				result = new CustomerDTO();
				result.setMessage("Customer Does not exist!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
		}catch(Exception e) {
			CustomerDTO exc = new CustomerDTO() ;
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@DeleteMapping("customer/{id}")
	public String deleteCustomer(@PathVariable("id") long id) {
		try {
			Customer c = custservice.getCustById(id);
			if(c.getBillingAddId()==c.getShippingAddId()) {

				custaddservice.deleteAddress(c.getBillingAddId());
			}
			else {
				
			custaddservice.deleteAddress(c.getBillingAddId());
			custaddservice.deleteAddress(c.getShippingAddId());
			}
			return custservice.deleteCustomer(id);
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}

}
