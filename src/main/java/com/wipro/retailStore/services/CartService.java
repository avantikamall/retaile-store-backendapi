package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.Cart;
import com.wipro.retailStore.repo.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartrepo;
	
	public Cart addcart(Cart c) {
		return cartrepo.save(c);
	}
	
	public Cart searchCart(int cartId) {
		
		return cartrepo.findById(cartId).isPresent()?cartrepo.findById(cartId).get():null;
	}
	
public Cart searchCartbycustId(long cartId) {
		
		return cartrepo.findByCustId(cartId);
	}
	
	public List<Cart> getAllCart(){
		List<Cart> cartList = new ArrayList<>();
		cartrepo.findAll().forEach(cl->cartList.add(cl));
		return cartList;
	}
	
	public String emptyCart(int cartId) {
		if(cartrepo.findById(cartId).isPresent()) {
			Cart c = cartrepo.findById(cartId).get();
			Cart newc = new Cart(cartId, c.getCustId(), null);
			cartrepo.save(newc);
			return "Empty successfully";
		}else {
			return "Cart does not exist!!";
		}
	}
	
	
	public Cart updateCart(Cart cartdetails) {
		if(cartrepo.findById(cartdetails.getCartId()).isPresent()) {
			Cart newCart = new Cart(cartdetails.getCartId(), cartdetails.getCustId(), cartdetails.getLineItemId());
			return cartrepo.save(newCart);
		}
		else {
			return null;
		}
	}
	 
	
}
