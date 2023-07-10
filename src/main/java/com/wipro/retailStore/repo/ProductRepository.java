package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
