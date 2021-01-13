package com.egas.dealer.repository;

import org.springframework.data.repository.CrudRepository;

import com.egas.dealer.entity.Customer;





public interface CustomerRepository extends CrudRepository<Customer,Integer>{

	public Customer findByPancardNumber(String pancardNumber);
}
