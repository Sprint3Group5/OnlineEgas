package com.egas.dealer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.CustomerNewConnectionRepository;
import com.egas.dealer.repository.DealerRepository;
import com.egas.dealer.repository.StaffMemberRepository;


@Service
public class IDealerServiceImpl  implements IDealerService{

	static String pancardNumberLogin;
	@Autowired
	DealerRepository dealerRepository;
	@Autowired
	private CustomerNewConnectionRepository customerRepository;
	@Autowired
	private StaffMemberRepository DeliveryStaffRepository;
	@Autowired
	private CustomerAccessoriesBookingRepository custAccRepository;
	@Autowired
	private CustomerGasBookingRepository custGasRepository;
	
	public Dealer addDealer(Dealer dealer)
	{
		
		return dealerRepository.save(dealer);
		
	}
	
	
	public Dealer updateDealer(Map<String,String> updateData)
	{
		Dealer dealer=new Dealer();
		String pancardNumber=updateData.get("pancardNumber");
		String updatedFname=updateData.get("firstName");
		String updatedLname=updateData.get("lastName");
		String updatedEmail=updateData.get("email");
		String updatedContact=updateData.get("contactNumber");
		String updatedPassword=updateData.get("password");
		String updatedConfirmPassword=updateData.get("confirmPassword");
		if(pancardNumber==null)
		{
			throw new InputException("Please Enter Pancard Number");
		}
		
		try
		{
		dealer=dealerRepository.findByPancardNumber(pancardNumber);
		
		if(!StringUtils.isEmpty(updatedFname))
		{
			dealer.setFirstName(updatedFname);
		}
		if(!StringUtils.isEmpty(updatedLname))
		{
			dealer.setLastName(updatedLname);
		}
		if(!StringUtils.isEmpty(updatedEmail))
		{
			dealer.setEmail(updatedEmail);
		}
		if(!StringUtils.isEmpty(updatedContact))
		{
			dealer.setContactNumber(updatedContact);
		}
		if(!StringUtils.isEmpty(updatedPassword) && !StringUtils.isEmpty(updatedConfirmPassword))
		{
			if(!(updatedPassword.equals(updatedConfirmPassword)))
			{
				throw new InputException("Password and Confirm Password not Matching");
			}
			dealer.setPassword(updatedPassword);
			dealer.setConfirmPassword(updatedConfirmPassword);
		}
		else if(!StringUtils.isEmpty(updatedPassword) && StringUtils.isEmpty(updatedConfirmPassword))
		{
			throw new InputException("Please Provide Confirm Password...");
		}
		else if(StringUtils.isEmpty(updatedPassword) && !StringUtils.isEmpty(updatedConfirmPassword))
		{
			throw new InputException("Please Provide Password...");
		}
		
		dealerRepository.save(dealer);
	    }
		catch(NotFoundException e)
		{
			throw new NotFoundException("Pancard Number not Found :( ... Please Enter Valid Pancard Number!");
		}
		return dealer;
		
	}
	
	public List<Dealer> getAllDealers()
	{
		List<Dealer> dealer=(List<Dealer>)dealerRepository.findAll();
		return dealer;
	}
	
	public String dealerLogin(Map<String,String> loginData) 
	{
		
		pancardNumberLogin=loginData.get("pancardNumber");
		String password = loginData.get("password");
		Dealer dealer1=dealerRepository.findByPancardNumber(pancardNumberLogin);
		//System.out.println(dealer1);
		Boolean dealer_found = false;
		if(dealer1==null)
		{
			throw new NotFoundException("...Login Fail...If you are new user do register first:)");
		}
		if(pancardNumberLogin==null)
		 {
			 throw new InputException("Please enter Pancard Number");
		 }
		if(password==null)
		 {
			 throw new InputException("Please enter Password");
		 }
		if(dealer1.getDealerStatus().equals("Pending"))
		{
			throw new NotFoundException("Dear Dealer your Registration is not yet approved by admin...Once Approved you will be able to login :)");
		}
		List<Dealer> dealer = (List<Dealer>) dealerRepository.findAll();
        for (Dealer temp : dealer) {

			if(temp.getPancardNumber().equals(pancardNumberLogin) && temp.getPassword().equals(password))
			{
			    dealer_found=true;
			    return pancardNumberLogin;
			}	
        }
        if(dealer_found==false) {
     
			throw new NotFoundException("...Login Fail...If you are new user do register first:)");

        }
        return pancardNumberLogin;

	}
	
	//*********Customer New Connection Methods***********
	public CustomerNewConnection saveConnectionStatus(Map<String,String> updateStatus)
	{
		String status=updateStatus.get("custConnectionStatus");
		String pancardNumber=updateStatus.get("custPancard");
		CustomerNewConnection customer=customerRepository.findByCustPancard(pancardNumber);
		if(customer != null) {
			customer.setCustConnectionStatus(status);
			customerRepository.save(customer);
		}
		return 	customer;
	
	}
	public List<CustomerNewConnection> getAllCustomerConnections()
	{
		Dealer dealer=dealerRepository.findByPancardNumber(pancardNumberLogin);
		System.out.println(pancardNumberLogin);
		String city=dealer.getCity();
		List<CustomerNewConnection> customerCon=(List<CustomerNewConnection>)customerRepository.findAllByCustCity(city);
		List<CustomerNewConnection> customer=new ArrayList<>();
		//List<Dealer> dealer = (List<Dealer>) dealerRepository.findAll();
        for (CustomerNewConnection temp : customerCon) {

			if(temp.getCustConnectionStatus().equals("Approved"))
			{
			    customer.add(temp);
			}	
        }
		return customer;
	}
	
	public List<CustomerNewConnection> getPendingConnections()
	{
		Dealer dealer=dealerRepository.findByPancardNumber(pancardNumberLogin);
		System.out.println(pancardNumberLogin);
		String city=dealer.getCity();
		List<CustomerNewConnection> customerCon=(List<CustomerNewConnection>)customerRepository.findAllByCustCity(city);
		List<CustomerNewConnection> customer=new ArrayList<>();
		//List<Dealer> dealer = (List<Dealer>) dealerRepository.findAll();
        for (CustomerNewConnection temp : customerCon) {

			if(temp.getCustConnectionStatus().equals("Pending"))
			{
			    customer.add(temp);
			}	
        }
		return customer;
	}
	
	//*********Customer Gas Booking Methods***********
	
	public List<CustomerGasBooking> getAllCustomerBookings()
	{
		List<CustomerGasBooking> custBooking=(List<CustomerGasBooking>) custGasRepository.findAll();
		return custBooking;
	}
	public CustomerGasBooking saveGasBookingStatus(Map<String,String> updateStatus)
	{
		String status=updateStatus.get("custGasBookingStatus");
		String pancardNumber=updateStatus.get("custPancard");
		CustomerGasBooking accessories = custGasRepository.findByCustPancard(pancardNumber);
		if(accessories != null) {
			accessories.setCustGasBookingStatus(status);
			custGasRepository.save(accessories);
		}
		return 	accessories;
	
	}
	
	
	//*********Customer Accessories Methods************
	
	public List<CustomerAccessoriesBooking> getAllCustomerAccessories()
	{
		List<CustomerAccessoriesBooking> custAccessories=(List<CustomerAccessoriesBooking>) custAccRepository.findAll();
		return custAccessories;
	}
	
	public CustomerAccessoriesBooking saveAccessoriesStatus(Map<String,String> updateStatus)
	{
		String status=updateStatus.get("custAccessoriesBookingStatus");
		String pancardNumber=updateStatus.get("custPancard");
		CustomerAccessoriesBooking accessories = custAccRepository.findByCustPancard(pancardNumber);
		if(accessories != null) {
			accessories.setCustAccessoriesBookingStatus(status);
			custAccRepository.save(accessories);
		}
		return 	accessories;
	
	}

	//**********Delivery Staff Methods***********
    
    public List<Staff_Member> getAllDeliveryStaff()
	{
		List<Staff_Member> deliveryStaff=(List<Staff_Member>) DeliveryStaffRepository.findAll();
		return deliveryStaff;
	}
	public Staff_Member saveStaffStatus(Map<String,String> updateStatus)
	{
		String status=updateStatus.get("status");
		String pancardNumber=updateStatus.get("pancardNumber");
		Staff_Member staff =  DeliveryStaffRepository.findByPancardNumber(pancardNumber);
		if(staff != null) {
			staff.setStatus(status);
			DeliveryStaffRepository.save(staff);
		}
		return 	staff;
	
	}

	

}
