package com.wipro.retailStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.retailStore.repo.LineItemRepository;

@Service
public class LineItemService {

	@Autowired
	LineItemRepository lineItemrepo;
}
