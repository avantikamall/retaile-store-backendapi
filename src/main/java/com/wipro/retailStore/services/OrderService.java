package com.wipro.retailStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	
}
