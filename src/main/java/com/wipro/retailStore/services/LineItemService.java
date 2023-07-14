package com.wipro.retailStore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.entity.LineItem;
import com.wipro.retailStore.repo.LineItemRepository;

@Service
public class LineItemService {

	@Autowired
	LineItemRepository lineItemrepo;
	
	public LineItem additem(LineItem item) {
		return lineItemrepo.save(item);
	}
	
	public LineItem searchItem(int itemId) {
		return lineItemrepo.findById(itemId).isPresent()?lineItemrepo.findById(itemId).get():null;
	}
	
	public List<LineItem> getAllitem() {
		List<LineItem> ln = new ArrayList<>();
		lineItemrepo.findAll().forEach(item->ln.add(item));
		return ln;
	}
	
	public LineItem updateItem(LineItem item) {
		if(lineItemrepo.findById(item.getItemId()).isPresent()) {
			return lineItemrepo.save(item);
		}else {
			return null;
		}
	}
	
	public String deleteItem(int itemId) {
		if(lineItemrepo.findById(itemId).isPresent()) {
			lineItemrepo.deleteById(itemId);
			return "Item deleted Successfully!!";
		}else {
			return "Item does not exist!!";
		}
	}
	
}
