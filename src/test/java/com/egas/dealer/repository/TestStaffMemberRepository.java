package com.egas.dealer.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.Staff_Member;



@DataJpaTest
@RunWith(SpringRunner.class)
public class TestStaffMemberRepository {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private StaffMemberRepository staffRepository;
	
	@Autowired
	private CustomerGasBookingRepository gasRepository;
	
	
	@BeforeEach
	void setUp() throws Exception{
		entityManager.persist(new Staff_Member("HGTRE3434T","Shruti","Gupta","female","Nagpur","Shruti@123","guptashruti806@gmail.com","9373829292"));
		entityManager.persist(new Staff_Member("PHVBT2020R","Shrishti","Prasad","female","Mumbai","Shrishti@123","Shrishti123@gmail.com","7392902090"));
		entityManager.persist(new CustomerGasBooking("GHTRE6090T","Shivani","Manchewar","Maharashtra","Mumbai","Shivaji Chowk","9362828283","Not Approved","Not Delivered"));
	}
	
	@Test
	@DisplayName("Checking City for the given Pancard Number")
	void testFindByPancardNumber()
	{
		Staff_Member staff=staffRepository.findByPancardNumber("HGTRE3434T");
		assertThat(staff.getCity()).isEqualTo("Nagpur");
	}
	
	@Test
	@DisplayName("Pancard Number passed is not Null")
	void test1FindByPancardNumber()
	{
		Staff_Member staff=staffRepository.findByPancardNumber("PHVBT2020R");
		assertNotNull(staff);
	}
	
	@Test
	@DisplayName("Null value passed in Pancard Number Field")
	void test2FindByPancardNumber()
	{
		Staff_Member staff=staffRepository.findByPancardNumber(" ");
		assertNull(staff);
	}
	
    @Test
    @DisplayName("Finding customer by city")
    void testFindByCustCity()
    {
    	CustomerGasBooking custGas=(CustomerGasBooking) gasRepository.findByCustPancard("GHTRE6090T");
    	assertThat(custGas.getCustCity()).isEqualTo("Mumbai");
    	
    }
}
