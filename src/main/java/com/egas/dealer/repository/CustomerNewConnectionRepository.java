package com.egas.dealer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.egas.dealer.entity.CustomerNewConnection;



public interface CustomerNewConnectionRepository extends CrudRepository<CustomerNewConnection,Integer>{
	
	public List<CustomerNewConnection> findAllByCustCity(String custCity);
	public CustomerNewConnection findByCustPancard(String custPancard);

}

