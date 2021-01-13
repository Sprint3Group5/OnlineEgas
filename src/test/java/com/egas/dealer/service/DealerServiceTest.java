package com.egas.dealer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.CustomerNewConnectionRepository;
import com.egas.dealer.repository.DealerRepository;
import com.egas.dealer.repository.StaffMemberRepository;
import com.egas.dealer.service.IDealerServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class DealerServiceTest {

	@Autowired
	private IDealerServiceImpl dealerService;
	
	@MockBean
	private DealerRepository dealerRepository;
	
	@MockBean
	private CustomerNewConnectionRepository connectionRepository;
	
	@MockBean
	private CustomerAccessoriesBookingRepository custAccessoriesRepo;
	
	@MockBean
	private CustomerGasBookingRepository custGasBookingRepo;
	
	@MockBean
	private StaffMemberRepository deliveryStaffRepo;
	
	
	@Test
	@DisplayName("Update Contact Number and Email")
	public void updateFirstNameAndEmail()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("pancardNumber", "ABCDE1234J");
		updateData.put("contactNumber", "7806137700");
		updateData.put("email", "shivangi@gmail.com");
		when(dealerRepository.findByPancardNumber("ABCDE1234J")).thenReturn(new Dealer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","8806137600","shiva","shiva","Thane","Maharashtra"));
		Dealer dealer=dealerService.updateDealer(updateData);
		when(dealerRepository.save(dealer)).thenReturn(new Dealer("ABCDE1234J","Shivangi","Manchekar","Female","shivangi@gmail.com","8806137600","shiva","shiva","Thane","Maharashtra"));
		assertThat(dealer.getContactNumber()).isEqualTo("7806137700");
		assertThat(dealer.getEmail()).isEqualTo("shivangi@gmail.com");
	}
	
	@DisplayName("View All Customer Connections Test")
	@Test
	public void getAllCustomerConnectionsTest()
	{
		IDealerServiceImpl.pancardNumberLogin="ABCDE1234J";
		when(dealerRepository.findByPancardNumber("ABCDE1234J")).thenReturn(new Dealer("ABCDE1234J","Shivani",
				"Manchewar","Female","shivani@gmail.com","8806137600","shiva","shiva","Thane","Maharashtra"));
		when(connectionRepository.findAllByCustCity("Thane")).thenReturn(Stream.of(
				new CustomerNewConnection("LAXMA6789N","Laxman","Manchewar","Maharashtra","Thane","Parsikh Nagar, Thane","8806137600"),
				new CustomerNewConnection("RENUK4567A","Renuka","Manchewar","Maharashtra","Thane","Tilak Nagar, Thane","7806189670"))
				.collect(Collectors.toList()));
		List<CustomerNewConnection> customers=dealerService.getAllCustomerConnections();
		assertEquals(2,customers.size());
	}

	@DisplayName("Connection Status test for Approved")
	@Test void saveConnectionStatusTest()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("custPancard", "LAXMA6789N");
		updateData.put("custConnectionStatus", "Approved");
		when(connectionRepository.findByCustPancard("LAXMA6789N")).thenReturn(new CustomerNewConnection("LAXMA6789N","Laxman","Manchewar","Maharashtra","Thane","Parsikh Nagar, Thane","8806137600"));
		CustomerNewConnection customer=dealerService.saveConnectionStatus(updateData);
		assertThat(customer.getCustConnectionStatus()).isEqualTo("Approved");
	}
	@Test
	public void GetAllDealersTest() 
	{
		when(dealerRepository.findAll()).thenReturn(Stream.of(
				new Dealer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","8806137600","shiva","shiva","Thane","Maharashtra"),
				new Dealer("PQRS4567T","Gayatri","Manchewar","Female","gayatri@gmail.com","8806137600","gayu","gayu","Mulund","Maharashtra"))
				.collect(Collectors.toList()));
		List<Dealer> dealers=dealerService.getAllDealers();
		assertEquals(2,dealers.size());
	}
	
	@Test
	@DisplayName("Throw Exception when Pancard Passed is Null")
	public void updateDealerTest2()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("firstName", "Shivangi");
		updateData.put("email", "shivangi@gmail.com");
		Assertions.assertThrows(Exception.class,() -> {
			dealerService.updateDealer(updateData);
	    });		
	}
	@Test
	@DisplayName("Throw Exception when Password and confirm password not same")
	public void updateDealerTest3()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("password", "Shivangi");
		updateData.put("confirmPassword", "shivan");
		Assertions.assertThrows(Exception.class,() -> {
			dealerService.updateDealer(updateData);
	    });		
	}
	
	//Pratiksha
	
	@Test
	public void getAllCustomerAccessoriesTest() 
	{
		when(custAccessoriesRepo.findAll()).thenReturn(Stream.of(
				new CustomerAccessoriesBooking("ABCDE1234J","Shivani","Manchewar","Maharashtra","Thane","Ram Nagar","8806137600","Burner","Pending","Not Delivered"),
				new CustomerAccessoriesBooking("PQRS4567T","Gayatri","Manchekar","Maharashtra","Thane","Shree Nagar","8586963256","Stove","Pending","Not Delivered"))
				.collect(Collectors.toList()));
		List<CustomerAccessoriesBooking> custAccessories=dealerService.getAllCustomerAccessories();
		assertEquals(2,custAccessories.size());
	}
	
	
	@Test
	public void getAllCustomerBookingsTest() 
	{
		when(custGasBookingRepo.findAll()).thenReturn(Stream.of(
				new CustomerGasBooking("ABCDE1234J","Shivani","Manchewar","Ram Nagar","Thane","Maharashtra","8806137600","Pending","Not Delivered"),
				new CustomerGasBooking("PQRS4567T","Gayatri","Manchekar","Shree Nagar","Thane","Maharashtra","8586963256","Pending","Not Delivered"))
				.collect(Collectors.toList()));
		List<CustomerGasBooking> custGasBooking=dealerService.getAllCustomerBookings();
		assertEquals(2,custGasBooking.size());
	}
	
	
	@Test
	public void getAllDeliveryStaffTest() 
	{
		when(deliveryStaffRepo.findAll()).thenReturn(Stream.of(
				new Staff_Member("XYXYX4242X","Shiva","Patil","Male","Thane","shiva@gmail,com","8806137600","Pending"),
				new Staff_Member("STSTS4141S","Sunil","Jadhav","Male","Thane","sunil@gmail.com","8586963256","Pending"))
				.collect(Collectors.toList()));
		List<Staff_Member> deliveryStaff=dealerService.getAllDeliveryStaff();
		assertEquals(2,deliveryStaff.size());
	}
}
