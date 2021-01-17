package com.egas.dealer.service;

import java.util.List;
import java.util.Map;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;



public interface ICustomerService {
	
	public void addCustomer(Customer customer);
	public CustomerNewConnection newConnection(CustomerNewConnection customerNewConnection);
	public CustomerGasBooking gasBooking(CustomerGasBooking customerGasBooking);
	public CustomerAccessoriesBooking accessoriesBooking(CustomerAccessoriesBooking customerAccessoriesBooking);
	public String customerLogin(String pancardNumber,String password);
	public Customer updateCustomer(Map<String,String> updateData);
	public void addCustomerNewConnection(Customer customer);
	public String getCustomerName(String pancardnumber);
}
