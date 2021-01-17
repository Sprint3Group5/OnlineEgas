package com.egas.dealer.service;

import java.util.List;
import java.util.Map;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;

public interface IDealerService {
	
	public List<Dealer> getAllDealers();
	public Dealer addDealer(Dealer dealer);
	public Dealer updateDealer(Map<String,String> updateFname);
	public List<CustomerNewConnection> getAllCustomerConnections();
	public CustomerNewConnection saveConnectionStatus(Map<String,String> data);
	public String dealerLogin(String pancard,String pass);
	public List<CustomerGasBooking> getAllCustomerBookings();
	public CustomerGasBooking saveGasBookingStatus(Map<String,String> updateStatus);
	public List<CustomerAccessoriesBooking> getAllCustomerAccessories();
	public CustomerAccessoriesBooking saveAccessoriesStatus(Map<String,String> updateStatus);
	public List<Staff_Member> getAllDeliveryStaff();
	public Staff_Member saveStaffStatus(Map<String,String> updateStatus);
	
	
}
