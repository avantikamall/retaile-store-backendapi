package com.wipro.retailStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.repo.CustomerAddressRepository;

@Service
public class CustomerAddressService {

	@Autowired
	CustomerAddressRepository custAddrepo;
	
}
