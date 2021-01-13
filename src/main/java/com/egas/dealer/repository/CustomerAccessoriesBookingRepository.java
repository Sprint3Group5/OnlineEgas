package com.egas.dealer.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egas.dealer.entity.CustomerAccessoriesBooking;



@Repository
public interface CustomerAccessoriesBookingRepository extends CrudRepository<CustomerAccessoriesBooking,Integer>
{
	public CustomerAccessoriesBooking findByCustPancard(String custPancard);
	public List<CustomerAccessoriesBooking> findByCustBookingCity(String custBookingCity);
}
