package com.wipro.retailStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.retailStore.entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>{

}
