package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.Product;
import com.wipro.retailStore.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productrepo;
	
	public Product addProduct(Product product) {
		return productrepo.save(product);
	}
	
	public Product searchProduct(int pId) {
		return productrepo.findById(pId).isPresent()?productrepo.findById(pId).get():null;
	}
	
	public List<Product> getAllProduct(){
		List<Product> plist = new ArrayList<>();
		productrepo.findAll().forEach(p->plist.add(p));
		return plist;
	}
	
	public Product updateProduct(Product p) {
		if(productrepo.findById(p.getProductId()).isPresent()) {
			return productrepo.save(p);
		}else {
			return null;
		}
	}
	public String deleteProduct(int pId) {
		if(productrepo.findById(pId).isPresent()) {
			productrepo.deleteById(pId);
			return "Product Deleted Successfully!";
		}else {
			return "Product does not exist!!";
		}
	}
	
}
