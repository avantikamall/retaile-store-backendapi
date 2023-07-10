package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
