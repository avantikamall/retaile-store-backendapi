package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

	Inventory findByProductId(int productId);
}
