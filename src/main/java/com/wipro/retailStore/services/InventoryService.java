package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.Inventory;
import com.wipro.retailStore.repo.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryrepo;
	
	public Inventory addInventory(Inventory inventory) {
		return inventoryrepo.save(inventory);
	}
	
	public Inventory getInventoryById(int invId) {
		Inventory in = new Inventory(0,0,0);
		return inventoryrepo.findById(invId).isPresent()?inventoryrepo.findById(invId).get():in;
	}
	public List<Inventory> getAllInventory(){
		List<Inventory> invList = new ArrayList<>();
		inventoryrepo.findAll().forEach(inv->invList.add(inv));
		return invList;
	}
	
	public Inventory updateInventory(Inventory inv) {
		if(inventoryrepo.findById(inv.getInventoryId()).isPresent()) {
			return inventoryrepo.save(inv);
		}else {
			return null;
		}
	}
	public String deleteInventory(int invId) {
		if(inventoryrepo.findById(invId).isPresent()) {
			inventoryrepo.deleteById(invId);
			return "Inventory Deleted Successfully";
		}else {
			return "Inventory Does not Exists!!";
		}
	}
	
	
}
