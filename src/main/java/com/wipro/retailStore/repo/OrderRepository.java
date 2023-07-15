package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	Orders findByCustId(long custId);

}
	