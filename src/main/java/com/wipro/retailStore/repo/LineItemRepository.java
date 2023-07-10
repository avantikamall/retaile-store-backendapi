package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Integer>{

}
