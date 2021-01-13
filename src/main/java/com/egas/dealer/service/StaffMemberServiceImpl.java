package com.egas.dealer.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.StaffMemberRepository;


@Service
public class StaffMemberServiceImpl {
	
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
	
	public Staff_Member updateStaffMember(Map<String,String> updateData)
	{
		Staff_Member staffmember = new Staff_Member();
		String pancardNumber=updateData.get("pancardNumber");
		String updatedCity=updateData.get("city");
		String updatedContact=updateData.get("contact");
		String updatedEmail=updateData.get("email");
		String updatedPassword=updateData.get("password");
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
	
	public boolean staffMemberLogin(String pancardNumber,String password)
	{
		Boolean staff_found= false;
		List<Staff_Member> staffmembers=(List<Staff_Member>)staffRepository.findAll();
		for(Staff_Member staff : staffmembers)
		{
			if((staff.getPancardNumber().equals(pancardNumber)) && (staff.getPassword().equals(password)))
			{
				staff_found=true;
				break;
			}
		}
		return staff_found;
	}
	
	public List<CustomerGasBooking> getAllCustomerGasBooking()
	{
		List<CustomerGasBooking> gasBooking=(List<CustomerGasBooking>)custGasRepository.findAll();
		return gasBooking;
	
	}
	
	
	 public List<CustomerGasBooking> findByCustCity(String custCity) 
	 {
	   List<CustomerGasBooking> gasBook =custGasRepository.findByCustCity(custCity);
	   return gasBook;
	 }
	 
	 public List<CustomerAccessoriesBooking> getAllCustomerAccessoriesBooking()
	 {
		 List<CustomerAccessoriesBooking> accessoriesBooking=(List<CustomerAccessoriesBooking>)custAccessoriesRepository.findAll();
		 return accessoriesBooking;
	 }
	 
	 public List<CustomerAccessoriesBooking> findByCustBookingCity(String custBookingCity)
	 {
		 List<CustomerAccessoriesBooking> accessoriesBook=custAccessoriesRepository.findByCustBookingCity(custBookingCity);
		 return accessoriesBook;
	 }
	 
	 public CustomerGasBooking updateGasDeliveryStatus(Map<String,String> updateData)
	 {
		 String pancardNumber=updateData.get("custPancard");
		 String updatedStatus=updateData.get("custGasDeliveryStatus");
		 CustomerGasBooking connect=new CustomerGasBooking();
		 if(pancardNumber==null)
		 {
			 throw new InputException("Please enter Pancard Number");
		 }
		 CustomerGasBooking custGas=custGasRepository.findByCustPancard(pancardNumber);
		 if(custGas==null)
		 {
			 throw new NotFoundException("Pancard Number Not Found...Please Enter Valid Pancard Number for updating data..");
		 }
		 else
		 {
			 custGas.setCustGasDeliveryStatus(updatedStatus);
			 custGasRepository.save(custGas);
			 connect=custGas;
		 }
		 return connect;
	 }
	 
	 public CustomerAccessoriesBooking updateAccessoriesDeliveryStatus(Map<String,String> updateData)
	 {
		 String pancardNumber=updateData.get("custPancard");
		 String status=updateData.get("custAccessoriesDeliveryStatus");
		 CustomerAccessoriesBooking connect=new CustomerAccessoriesBooking();
		 if(pancardNumber==null)
		 {
			 throw new InputException("Please enter Pancard Number");
		 }
		 CustomerAccessoriesBooking custAccessories=custAccessoriesRepository.findByCustPancard(pancardNumber);
		 if(custAccessories==null)
		 {
			 throw new NotFoundException("Pancard Number Not Found...Please Enter Valid Pancard Number for updating data..");
		 }
		 else
		 {
			 custAccessories.setCustAccessoriesDeliveryStatus(status);
			 custAccessoriesRepository.save(custAccessories);
			 connect=custAccessories;
		 }
		 return connect;
	 }
}
