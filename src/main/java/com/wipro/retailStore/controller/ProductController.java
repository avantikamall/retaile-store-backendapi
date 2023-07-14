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

import com.wipro.retailStore.entity.Product;
import com.wipro.retailStore.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService pservice;
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		try {
			
			Product adp =  pservice.addProduct(p);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(adp);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> searchProduct(@PathVariable("id") int pId){
		try {
			Product p = pservice.searchProduct(pId);
			if(p!=null) {
				return ResponseEntity.status(200).body(p);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") int pId, Product p){
		try {
			Product pr = pservice.searchProduct(pId);
			if(pr!=null) {
				Product up = new Product(pr.getProductId(), pr.getProductName(),p.getProductDescription(),p.getProductPrice());
				return ResponseEntity.status(200).body(up);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("{id}")int pId){
		
		try {
			
			return ResponseEntity.status(200).body(pservice.deleteProduct(pId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
