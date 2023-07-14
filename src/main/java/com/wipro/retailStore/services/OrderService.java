package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.Order;
import com.wipro.retailStore.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	public Order addOrder(Order order) {
		return orderRepo.save(order);
	}
	
	public Order searcOrder(long orderId) {
		return orderRepo.findById(orderId).isPresent()?orderRepo.findById(orderId).get():null;
	}
	
	public Order searcOrderByCustId(long custId) {
		return orderRepo.findByCustId(custId);
	}
	
	public List<Order> getAllOrder(){
		List<Order> orderList = new ArrayList<>();
		orderRepo.findAll().forEach(ol->orderList.add(ol));
		return orderList;
	}
	
	public Order updateOrder(Order order) {
		if(orderRepo.findById(order.getOrderId()).isPresent()) {
			return orderRepo.save(order);
		}else {
			return null;
		}
	}
	
	public String deleteOrder(long orderId) {
		if(orderRepo.findById(orderId).isPresent()) {
			orderRepo.deleteById(orderId);
			return "Order Deleted Successfully";
		}else {
			return "Order does not exist!!";
		}
	}
	
}
