package com.egas.dealer.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.StaffMemberRepository;



@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStaffMemberServiceImpl {

	@Autowired
	private StaffMemberServiceImpl staffMemberService;
	
	@MockBean
	private StaffMemberRepository staffMemberRepository;
	
	@MockBean
	private CustomerGasBookingRepository gasRepository;
	
	@MockBean
	private CustomerAccessoriesBookingRepository accessoriesRepository;
	
	@Test
	public void testSaveStaffMember()
	{
		Staff_Member s1=new Staff_Member("GHTRE7895T","Ayush","Shrivastava","male","Mumbai","ayush123@gmail.com","9403802748","ayush$321");
	
		when(staffMemberRepository.save(s1)).thenReturn(s1);
		Staff_Member s2=staffMemberService.saveStaffMember(s1);
		
		assertEquals(s1,s2);
	}
	
	
	@Test
	public void testGetAllStaffMember()
	{
		when(staffMemberRepository.findAll()).thenReturn(Stream.of(new Staff_Member("GHTWE4060T","Siddharth","Prasad","male","Pune","siddharth123@gmail.com","8392329392","sid@123")).collect(Collectors.toList()));
		List<Staff_Member> staffmembers = staffMemberService.getAllStaffMember();
		assertEquals(1,staffmembers.size());
	}
	
	@Test
	public void testUpdateCityAndContact()
	{
		HashMap<String, String> updateData = new HashMap<>();
		updateData.put("pancardNumber", "PJHTR2323T");
		updateData.put("city", "Mumbai");
		updateData.put("contact","9373828297");
		when(staffMemberRepository.findByPancardNumber("PJHTR2323T")).thenReturn(new Staff_Member("PJHTR2323T","Ayush","Shrivastava","male","Pune","ayush123@gmail.com","9403802748","ayush$321"));
		Staff_Member staffmember=staffMemberService.updateStaffMember(updateData);
		when(staffMemberRepository.save(staffmember)).thenReturn(new Staff_Member("PJHTR2323T","Ayush","Shrivastava","male","Mumbai","ayush123@gmail.com","9373828297","ayush$321"));
		assertThat(staffmember.getCity()).isEqualTo("Mumbai");
		assertThat(staffmember.getContact()).isEqualTo("9373828297");
	}

	@Test
	public void testStaffMemberLogin()
	{
		boolean expected = true;
		when(staffMemberRepository.findAll()).thenReturn(Stream.of(new Staff_Member("GHTRE2323T","Shruti","Gupta","female","Nagpur","Shruti@123","shruti806@gmail.com","9403802749")).collect(Collectors.toList()));
		boolean actual=staffMemberService.staffMemberLogin("GHTRE2323T", "Shruti@123");
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testFindByCustCity()
	{
		String custCity="Nagpur";
		when(gasRepository.findByCustCity(custCity)).thenReturn(Stream.of(new CustomerGasBooking("HTREW2323R","Shruti","Gupta","Maharashtra","Nagpur","Chatrapati Square","9384838332","Not Approved","Not Delivered")).collect(Collectors.toList()));
		assertEquals(1,staffMemberService.findByCustCity(custCity).size());
		
	}	
	
	@Test 
	public void testGetAllCustomerGasBooking() 
	{
	  when(gasRepository.findAll()).thenReturn(Stream.of(new CustomerGasBooking("HTREY4565T","Shruti","Gupta","Maharashtra","Nagpur","Chatrapati Square","8346378373","Not Approved","Not Delivered")).collect(Collectors.toList())); 
	  List<CustomerGasBooking> custGas =staffMemberService.getAllCustomerGasBooking();
	  assertEquals(1,custGas.size()); 
    }
	
	@Test
	public void testFindByCustBookingCity()
	{
		String custBookingCity="Mumbai";
		when(accessoriesRepository.findByCustBookingCity(custBookingCity)).thenReturn(Stream.of(new CustomerAccessoriesBooking("HGTRE2323T","Shrishti","Pandey","Maharashtra","Mumbai","Gandhi Chowk","8364738298","Regulator","Not Approved","Not Delivered")).collect(Collectors.toList()));
		assertEquals(1,staffMemberService.findByCustBookingCity(custBookingCity).size());
	}
	
	@Test
	public void testGetAllCustomerAccessoriesBooking()
	{
		when(accessoriesRepository.findAll()).thenReturn(Stream.of(new CustomerAccessoriesBooking("HTREY4565T","Shruti","Gupta","Maharashtra","Nagpur","Chatrapati Square","8346378373","Pipe","Not Approved","Not Delivered")).collect(Collectors.toList())); 
		List<CustomerAccessoriesBooking> custAccessories =staffMemberService.getAllCustomerAccessoriesBooking();
		assertEquals(1,custAccessories.size()); 
	}
	
	@Test
	public void testUpdateGasDeliveryStatus()
	{
		HashMap<String, String> updateData = new HashMap<>();
		updateData.put("custPancard", "JHTRE2323T");
		updateData.put("custGasDeliveryStatus","Delivered");
		when(gasRepository.findByCustPancard("JHTRE2323T")).thenReturn(new CustomerGasBooking("JHTRE2323T","Shruti","Sharma","Maharashtra","Nagpur","Shivaji Chowk","8436472823","Not Approved","Not Delivered"));
		CustomerGasBooking customerGas=staffMemberService.updateGasDeliveryStatus(updateData);
		assertThat(customerGas.getCustGasDeliveryStatus()).isEqualTo("Delivered");
		
	}
	
	@Test
	public void testUpdateAccessoriesDeliveryStatus()
	{
		HashMap<String, String> updateData = new HashMap<>();
		updateData.put("custPancard", "PJAGT5656R");
		updateData.put("custAccessoriesDeliveryStatus","Delivered");
		when(accessoriesRepository.findByCustPancard("PJAGT5656R")).thenReturn(new CustomerAccessoriesBooking("PJAGT5656R","Shivani","Manchewar","Maharashtra","Mumbai","Nehru Square","7489392292","Regulator","Not Approved","Not Delivered"));
		CustomerAccessoriesBooking custAccessories=staffMemberService.updateAccessoriesDeliveryStatus(updateData);
		assertThat(custAccessories.getCustAccessoriesDeliveryStatus()).isEqualTo("Delivered");
	}
}

