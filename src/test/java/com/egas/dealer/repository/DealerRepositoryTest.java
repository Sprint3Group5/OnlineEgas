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

import com.egas.dealer.entity.Dealer;

@DataJpaTest
@RunWith(SpringRunner.class)
class DealerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DealerRepository dealerRepository;

	@BeforeEach
	void setUp() throws Exception {
		entityManager.persist(new Dealer("SIDHI8967M","Sidhi","Mahadik","Female","sidhi@gmail.com","9901234567","sidhi","sidhi","Thane","Maharashtra"));
		entityManager.persist(new Dealer("PQRST4567T","Gayatri","Manchewar","Female","gayatri@gmail.com","8806137600","gayatri","gayatri","Mulund","Maharashtra"));
		entityManager.persist(new Dealer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","8806137600","shiva","shiva","Thane","Maharashtra"));
		
		
	}
	@Test
	@DisplayName("Checking Email for Pancard Number passed")
	void findByPancardNumberEmailTest() 
	{
		Dealer dealer= dealerRepository.findByPancardNumber("SIDHI8967M");
		assertThat(dealer.getEmail()).isEqualTo("sidhi@gmail.com");		
	}
	@Test
	@DisplayName("Checking First Name for Pancard Number passed")
	void findByPancardNumberNameTest() 
	{
		Dealer dealer= dealerRepository.findByPancardNumber("PQRST4567T");
		assertThat(dealer.getFirstName()).isEqualTo("Gayatri");		
	}
	@Test
	@DisplayName("Pancard Number passed is not Null")
	void findByPancardNumberNotNullTest() 
	{
		Dealer dealer= dealerRepository.findByPancardNumber("SIDHI8967M");
		assertNotNull(dealer);	
	}
	@Test
	@DisplayName("Pancard Number passed is Null")
	void findByPancardNumberNullTest() 
	{
		Dealer dealer= dealerRepository.findByPancardNumber("");
		assertNull(dealer);
		
	}
	
	

}
