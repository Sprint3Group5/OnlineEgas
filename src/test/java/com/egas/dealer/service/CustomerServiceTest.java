package com.egas.dealer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.repository.CustomerNewConnectionRepository;
import com.egas.dealer.repository.CustomerRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerServiceTest {

	@Autowired
	private ICustomerServiceImpl customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private CustomerNewConnectionRepository connectionRepository;
	
	@Test
	@DisplayName("Throw Exception when Pancard Passed is Null")
	public void updateCustomerTest2()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("firstName", "Deepikaa");
		updateData.put("email", "deepika.v@gmail.com");
		Assertions.assertThrows(Exception.class,() -> {
			customerService.updateCustomer(updateData);
	    });		
	}
	@Test
	@DisplayName("Throw Exception when Password and confirm password not same")
	public void updateCustomerTest3()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("password", "Deepu25");
		updateData.put("confirmPassword", "Deepu");
		Assertions.assertThrows(Exception.class,() -> {
			customerService.updateCustomer(updateData);
	    });		
	}
	@Test
	@DisplayName("Update Email")
	public void updateEmail()
	{
		HashMap<String, String> updateData = new HashMap<String, String>();
		updateData.put("pancardNumber", "ABCDE1234J");
		//updateData.put("lname", "Deepikaa");
		updateData.put("email", "deepika.v@gmail.com");
		when(customerRepository.findByPancardNumber("ABCDE1234J")).thenReturn(new Customer("ABCDE1234J","Mani","Deepika","Female","deepika@gmail.com","9542543446","deepu25","deepu25"));
		Customer customer=customerService.updateCustomer(updateData);
		when(customerRepository.save(customer)).thenReturn(new Customer("ABCDE1234J","Mani","Deepikaa","Female","deepika.v@gmail.com","9542543446","deepu25","deepu25"));
		//assertThat(customer.getLname()).isEqualTo("Deepikaa");
		assertThat(customer.getEmail()).isEqualTo("deepika.v@gmail.com");
	}
	
}
