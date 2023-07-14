package com.wipro.retailStore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.retailStore.DTO.OrderDTO;
import com.wipro.retailStore.entity.Order;
import com.wipro.retailStore.entity.LineItem;
import com.wipro.retailStore.services.LineItemService;
import com.wipro.retailStore.services.OrderService;

public class OrdersController {

	@Autowired
	OrderService oservice;
	
	@Autowired
	LineItemService lservice;
	
	@PostMapping("/Orders")
	public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO c){
		try {
			List<LineItem> item = new ArrayList<>();
			List <Integer> itemId = new ArrayList<>();
			for(int i=0; i<c.getLineItem().size(); i++) {
				LineItem l = lservice.additem(c.getLineItem().get(i)); 
				itemId.add(l.getItemId());
				item.add(l);
			}
			Order ac =new Order();
			ac.setLineItemId(itemId);
			Order cd = oservice.addOrder(ac);
			
			OrderDTO result = new OrderDTO("Items added to the Order", cd.getOrderId(),item);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}catch(Exception e){
			OrderDTO exc = new OrderDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@GetMapping("/Orders/{id}")
	public ResponseEntity<OrderDTO> searchOrder(@PathVariable("id") int id){
		try {
			
			if(oservice.searcOrder(id)!=null) {
				List<Integer> listItemid = oservice.searcOrder(id).getLineItemId();
				List<LineItem> lineItem = new ArrayList<>();
				
				for(int i=0; i<listItemid.size(); i++) {
					LineItem l = lservice.searchItem(listItemid.get(i));
					lineItem.add(l);
				}
				
				OrderDTO result = new OrderDTO("Result", id, lineItem);
				
				return ResponseEntity.status(200).body(result);
			}
			else {
				OrderDTO result = new OrderDTO();
				result.setMessage("Order does not exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}
			
			
		}catch(Exception e){
			OrderDTO exc = new OrderDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@PutMapping("/Orders/{id}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") int id, OrderDTO c){
		try {
		List<LineItem> litem = new ArrayList<>();
			if(oservice.searcOrder(id)!=null) {
				for(int i=0; i<c.getLineItem().size(); i++) {
					LineItem l = lservice.searchItem(c.getLineItem().get(i).getItemId());
					if(l!=null) {
						LineItem l2 = lservice.updateItem(l);
						litem.add(l2);
					}else {
						LineItem l3 = lservice.additem(c.getLineItem().get(i));
						litem.add(l3);
					}
				
				}
				OrderDTO result = new OrderDTO("Updated Successfully", id , litem);
				return ResponseEntity.status(200).body(result);
				
			}else {
				OrderDTO result = new OrderDTO();
				result.setMessage("Order does not exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}
		}catch(Exception e){
			OrderDTO exc = new OrderDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@DeleteMapping("orders/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id ) {
		try {
		return ResponseEntity.status(200).body(oservice.deleteOrder(id));	
		
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
