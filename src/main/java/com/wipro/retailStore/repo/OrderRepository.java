package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
