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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.retailStore.DTO.CartDTO;
import com.wipro.retailStore.entity.Cart;
import com.wipro.retailStore.entity.LineItem;
import com.wipro.retailStore.services.CartService;
import com.wipro.retailStore.services.LineItemService;

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartService cservice;
	
	@Autowired
	LineItemService lservice;
	
	@PostMapping("/carts")
	public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO c){
		try {
			List<LineItem> item = new ArrayList<>();
			List <Integer> itemId = new ArrayList<>();
			for(int i=0; i<c.getLineItem().size(); i++) {
				LineItem l = lservice.additem(c.getLineItem().get(i)); 
				itemId.add(l.getItemId());
				item.add(l);
			}
			Cart ac =new Cart();
			ac.setLineItemId(itemId);
			Cart cd = cservice.addcart(ac);
			
			CartDTO result = new CartDTO("Items added to the cart", cd.getCartId(),item);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}catch(Exception e){
			CartDTO exc = new CartDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@GetMapping("/carts/{id}")
	public ResponseEntity<CartDTO> searchCart(@PathVariable("id") int id){
		try {
			
			if(cservice.searchCart(id)!=null) {
				List<Integer> listItemid = cservice.searchCart(id).getLineItemId();
				List<LineItem> lineItem = new ArrayList<>();
				
				for(int i=0; i<listItemid.size(); i++) {
					LineItem l = lservice.searchItem(listItemid.get(i));
					lineItem.add(l);
				}
				
				CartDTO result = new CartDTO("Result", id, lineItem);
				
				return ResponseEntity.status(200).body(result);
			}
			else {
				CartDTO result = new CartDTO();
				result.setMessage("cart does not exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}
			
			
		}catch(Exception e){
			CartDTO exc = new CartDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@PutMapping("/carts/{id}")
	public ResponseEntity<CartDTO> updateCart(@PathVariable("id") int id, CartDTO c){
		try {
		List<LineItem> litem = new ArrayList<>();
			if(cservice.searchCart(id)!=null) {
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
				CartDTO result = new CartDTO("Updated Successfully", id , litem);
				return ResponseEntity.status(200).body(result);
				
			}else {
				CartDTO result = new CartDTO();
				result.setMessage("cart does not exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}
		}catch(Exception e){
			CartDTO exc = new CartDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@DeleteMapping("carts/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable("id") int id ) {
		try {
		return ResponseEntity.status(200).body(cservice.emptyCart(id));	
		
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
