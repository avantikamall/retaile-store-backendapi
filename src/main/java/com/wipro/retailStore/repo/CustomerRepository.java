package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
