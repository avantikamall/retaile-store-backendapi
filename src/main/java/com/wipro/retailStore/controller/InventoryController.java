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

import com.wipro.retailStore.entity.Inventory;
import com.wipro.retailStore.services.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	InventoryService iservice;
	
	@PostMapping("/inventory")
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inv){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(iservice.addInventory(inv));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/inventory/{id}")
	public ResponseEntity<Inventory> searchInventory(@PathVariable("id") int invId){
		try {
			Inventory p = iservice.getInventoryById(invId);
			if(p!=null) {
				return ResponseEntity.status(200).body(p);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	@PutMapping("/inventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable("id") int invId, Inventory inv){
		try {
			Inventory invr = iservice.getInventoryById(invId);
			if(invr!=null) {
				Inventory up = new Inventory(invr.getInventoryId(),invr.getProductId(),invr.getQuantity());
				return ResponseEntity.status(200).body(up);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable("{id}")int invId){
		
		try {
			
			return ResponseEntity.status(200).body(iservice.deleteInventory(invId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
