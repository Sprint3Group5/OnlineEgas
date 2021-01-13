package com.egas.dealer.service;

import java.util.List;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;





public interface IAdminService {

	
	public List<Dealer> getAllDealers();
	
	public List<Customer> getAllCustomers();
	
	public List<Staff_Member> getAllDeliveryStaff();
	
	public void  updateAdmin();
	
	//public List<CustomerAccessoriesBooking> getAllCustomerAccessoriesBooking();
	//List<CustomerGasBooking> getAllCustomerGasBooking();
}
