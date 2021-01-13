package com.egas.dealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egas.dealer.entity.Dealer;

@Repository
public interface DealerRepository extends CrudRepository<Dealer,Integer>{

	public Dealer findByPancardNumber(String pancardNumber);
	
}
