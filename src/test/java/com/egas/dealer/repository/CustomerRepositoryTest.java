package com.egas.dealer.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.egas.dealer.entity.Customer;



@DataJpaTest
@RunWith(SpringRunner.class)
class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	void setUp() throws Exception {
		entityManager.persist(new Customer("SIDHI8967M","Sreeja","Mahadik","Female","sidhi@gmail.com","9901234567","sidhi","sidhi"));
		entityManager.persist(new Customer("PQRST4567T","Gayatri","Manchewar","Female","gayatri@gmail.com","8806137600","gayu","gayu"));
		entityManager.persist(new Customer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","8806137600","shiva","shiva"));
		
		
	}
	@Test
	@DisplayName("Pancard Number passed is not Null")
	void findByPancardNumberTest1() 
	{
		Customer customer= customerRepository.findByPancardNumber("SIDHI8967M");
		assertNotNull(customer);	
	}
	@Test
	@DisplayName("Pancard Number passed is Null")
	void findByPancardNumberTest2() 
	{
		Customer customer= customerRepository.findByPancardNumber("");
		assertNull(customer);
		
	}
	@Test
	@DisplayName("Checking First Name for Pancard Number passed")
	void findByPancardNumberTest3() 
	{
		Customer customer= customerRepository.findByPancardNumber("PQRST4567T");
		assertThat(customer.getFname()).isEqualTo("Gayatri");		
	}
	
	@Test
	@DisplayName("Checking Email for Pancard Number passed")
	void findByPancardNumberTest4() 
	{
		Customer customer= customerRepository.findByPancardNumber("ABCDE1234J");
		assertThat(customer.getEmail()).isEqualTo("shivani@gmail.com");		
	}
	

}
