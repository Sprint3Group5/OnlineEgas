package com.egas.dealer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class DealerModuleApplication implements CommandLineRunner {
	
	@Autowired
	private DealerRepository repository;	
	@Autowired
	private CustomerNewConnectionRepository custrepo;
	@Autowired
	private StaffMemberRepository DeliveryStaffRepository;
	@Autowired
	private CustomerAccessoriesBookingRepository accRepository;
	@Autowired
	private CustomerGasBookingRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		//public CustomerGasBooking(Integer custGasBookingId, String custPancard, String custName, String custAddress,
				//String custCity, String custState, String custContact, String custBookingStatus, String custGasDeliveryStatus) {
		//repo.save(new CustomerGasBooking("ABHIS1234H","Abhishek Thakur","Parsikh Nagar, Thane","Thane","Maharashtra",
			//	"9087564378","Gas Cylinder","Pending","Not Delivered"));
	//	public CustomerNewConnection(String custPancard, String custFirstName,String custLastName, String custState, 
		//		String custCity,String custAddress, String custContact)
	//	custrepo.save(new CustomerNewConnection("ALISH1234A","Alisha","Kuvalekar","Maharashtra","Thane","Swastik park,Thane","9078569078"));
	}
	public static void main(String[] args) {
		SpringApplication.run(DealerModuleApplication.class, args);
	}

}
