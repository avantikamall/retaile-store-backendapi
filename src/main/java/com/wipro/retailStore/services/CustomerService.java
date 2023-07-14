package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.Customer;
import com.wipro.retailStore.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custrepo;
	
	public Customer addCustomer(Customer c) {
		return custrepo.save(c);
	}
	
	public Customer getCustById(long cId) {
//		Customer c = new Customer(0l,null,null,0,0);
		return custrepo.findById(cId).isPresent()?custrepo.findById(cId).get():null;
	}

	public List<Customer> getAllCustomer(){
		List<Customer> c = new ArrayList<>();
		custrepo.findAll().forEach(cust->c.add(cust));
		return c;
	}
	
	public String deleteCustomer(long cId) {
		if(custrepo.findById(cId).isPresent()) {
			custrepo.deleteById(cId);
			return "Customer Deleted Successfully";
		}else {
			return "Customer does not exist!";
		}
	}
	
	public Customer updateCostomer(Customer c) {
		if(custrepo.findById(c.getCustId()).isPresent()) {
			return custrepo.save(c);
		}else {
			return null;
		}
	}
	
}
