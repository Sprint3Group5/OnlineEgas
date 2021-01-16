package com.egas.dealer.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.StaffMemberRepository;


@Service
public class StaffMemberServiceImpl {
	
	static String pancardNumberLogin;
	@Autowired
	private CustomerGasBookingRepository custGasRepository;
	
	@Autowired
	private CustomerAccessoriesBookingRepository custAccessoriesRepository;

	@Autowired
	private StaffMemberRepository staffRepository;
	
	public List<Staff_Member> getAllStaffMember()
	{
		List<Staff_Member> staffmembers=(List<Staff_Member>)staffRepository.findAll();
		return staffmembers;
	}
	
	public Staff_Member saveStaffMember(Staff_Member staffmember)
	{
		if(staffmember.getPancardNumber()==null)
		{
			throw new InputException("Please enter pancard number");
		}
		else if(staffmember.getFname()==null || staffmember.getLname()==null)
		{
			throw new InputException("Please Enter StaffMember Full Name");
		}
		else if(staffmember.getGender()==null)
		{
			throw new InputException("Please enter gender");
		}
		else if(staffmember.getContact()==null)
		{
			throw new InputException("Please enter contact number");
		}
		else if(staffmember.getEmail()==null)
		{
			throw new InputException("Please Enter email Id");
		}
		else if(staffmember.getPassword()==null)
		{
			throw new InputException("Enter Password to keep the account secured and remember it during login");
		}
		try 
		{
			   return staffRepository.save(staffmember);
	    }catch(DataIntegrityViolationException e)
		{
	    	throw new InputException("This Username is already registered....Please Login....");
		}
	}
	
	public String staffLogin(String pancard,String pass)
	{
		pancardNumberLogin = pancard;
		String password = pass;
		Staff_Member staffmember=staffRepository.findByPancardNumber(pancardNumberLogin);
		Boolean staff_found= false;
		if(staffmember==null)
		{
			throw new NotFoundException("Login Fail...If you are new user do register first");
		}
		if(pancardNumberLogin==null)
		{
			throw new InputException("Please Enter pancard number");
		}
		if(password==null)
		{
			throw new InputException("Please enter password");
		}
		if(staffmember.getStatus().equals("Pending"))
		{
			throw new NotFoundException("Your registration is not yet approved by Dealer :)");
		}
		List<Staff_Member> staff=(List<Staff_Member>) staffRepository.findAll();
		for(Staff_Member s:staff)
		{
			if(s.getPancardNumber().equals(pancardNumberLogin) && s.getPassword().equals(password))
			{
				staff_found=true;
				return pancardNumberLogin;
			}
		}
		if(staff_found==false)
		{
			throw new NotFoundException("Login Fail...If you are new user do register first");
		}
		return pancardNumberLogin;
	}
	public Staff_Member updateStaffMember(Staff_Member updateData)
	{
		Staff_Member staffmember = new Staff_Member();
		String pancardNumber=updateData.getPancardNumber();
		String updatedCity=updateData.getCity();
		String updatedContact=updateData.getContact();
		String updatedEmail=updateData.getEmail();
		String updatedPassword=updateData.getPassword();
		if(pancardNumber==null)
		{
			throw new InputException("Please Enter Pancard Number"); 
		}
		try
		{
			staffmember=staffRepository.findByPancardNumber(pancardNumber);
			if(!StringUtils.isEmpty(updatedCity))
			{
				staffmember.setCity(updatedCity);
			}
			if(!StringUtils.isEmpty(updatedContact))
			{
				staffmember.setContact(updatedContact);
			}
			if(!StringUtils.isEmpty(updatedEmail))
			{
				staffmember.setEmail(updatedEmail);
			}
			if(!StringUtils.isEmpty(updatedPassword))
			{
				staffmember.setPassword(updatedPassword);
			}
			staffRepository.save(staffmember);
		}
		catch(Exception ex)
		{
			throw new NotFoundException("Pancard Number not Found.....Please Enter valid Pancard Number.....");
		}
		return staffmember;
	}
	
	public List<CustomerGasBooking> getAllCustomerGasBooking()
	{
		Staff_Member staffmember=staffRepository.findByPancardNumber(pancardNumberLogin);
		System.out.println(pancardNumberLogin);
		String city=staffmember.getCity();
		List<CustomerGasBooking> customerGas=(List<CustomerGasBooking>)custGasRepository.findByCustCity(city);
		List<CustomerGasBooking> customer=new ArrayList<>();
        for (CustomerGasBooking cust : customerGas) {

			if((cust.getCustGasBookingStatus().equals("Approved")) && (cust.getCustGasDeliveryStatus().equals("Delivered")))
			{
			    customer.add(cust);
			}	
        }
		return customer;
	
	}
	
	public List<CustomerGasBooking> getPendingGasBooking()
	{
		Staff_Member staffmember=staffRepository.findByPancardNumber(pancardNumberLogin);
		System.out.println(pancardNumberLogin);
		String city=staffmember.getCity();
		List<CustomerGasBooking> customerGas=(List<CustomerGasBooking>)custGasRepository.findByCustCity(city);
		List<CustomerGasBooking> customer=new ArrayList<>();
        for (CustomerGasBooking cust : customerGas) {

			if(cust.getCustGasDeliveryStatus().equals("Not Delivered") && cust.getCustGasBookingStatus().equals("Approved"))
			{
			    customer.add(cust);
			}	
        }
		return customer;
	}
	
	 
	 public List<CustomerAccessoriesBooking> getAllCustomerAccessoriesBooking()
	 {
		 Staff_Member staffmember=staffRepository.findByPancardNumber(pancardNumberLogin);
			System.out.println(pancardNumberLogin);
			String city=staffmember.getCity();
			List<CustomerAccessoriesBooking> customerAccessories=(List<CustomerAccessoriesBooking>)custAccessoriesRepository.findByCustBookingCity(city);
			List<CustomerAccessoriesBooking> customer=new ArrayList<>();
	        for (CustomerAccessoriesBooking cust : customerAccessories) {

				if((cust.getCustAccessoriesBookingStatus().equals("Approved")) && (cust.getCustAccessoriesDeliveryStatus().equals("Delivered")))
				{
				    customer.add(cust);
				}	
	        }
			return customer;
		 
	 }
	 public List<CustomerAccessoriesBooking> getPendingAccessoriesBooking()
		{
			Staff_Member staffmember=staffRepository.findByPancardNumber(pancardNumberLogin);
			System.out.println(pancardNumberLogin);
			String city=staffmember.getCity();
			List<CustomerAccessoriesBooking> customerAccessories=(List<CustomerAccessoriesBooking>)custAccessoriesRepository.findByCustBookingCity(city);
			List<CustomerAccessoriesBooking> customer=new ArrayList<>();
	        for (CustomerAccessoriesBooking cust : customerAccessories) {

				if(cust.getCustAccessoriesDeliveryStatus().equals("Not Delivered") && cust.getCustAccessoriesBookingStatus().equals("Approved"))
				{
				    customer.add(cust);
				}	
	        }
			return customer;
		}
	 
	 
	 public CustomerGasBooking updateGasDeliveryStatus(Map<String,String> updateData)
	 {
		 String status=updateData.get("custGasDeliveryStatus");
		 String pancardNumber=updateData.get("custPancard");
		 CustomerGasBooking cust=custGasRepository.findByCustPancard(pancardNumber);
		 if(cust != null)
		 {
			 cust.setCustGasDeliveryStatus(status);
			 custGasRepository.save(cust);
		 }
		 return cust;
	 }
	 
	 public CustomerAccessoriesBooking updateAccessoriesDeliveryStatus(Map<String,String> updateData)
	 {
		 String pancardNumber=updateData.get("custPancard");
		 String status=updateData.get("custAccessoriesDeliveryStatus");
		 CustomerAccessoriesBooking cust=custAccessoriesRepository.findByCustPancard(pancardNumber);
		 if(cust != null)
		 {
			 cust.setCustAccessoriesDeliveryStatus(status);
		 }
		 return cust;
	 }
}
