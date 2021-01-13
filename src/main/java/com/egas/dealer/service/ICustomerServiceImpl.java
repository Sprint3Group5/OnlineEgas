package com.egas.dealer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.CustomerNewConnectionRepository;
import com.egas.dealer.repository.CustomerRepository;



@Service 
public class ICustomerServiceImpl  implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	 CustomerNewConnectionRepository custNewRepository;
	
	@Autowired
	private CustomerGasBookingRepository custGasRepository;
	
	@Autowired
	private CustomerAccessoriesBookingRepository custAccessoriesRepository;
	
	
	public void addCustomer(Customer customer)
	{
		String password=customer.getPassword();
		String confirmPassword=customer.getConfirmPassword();
		if(!(password.equals(confirmPassword)))
		{
			throw new InputException("Password and Confirm Password not Matching");
		}
		try
		{
			customerRepository.save(customer);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new InputException("This Username is already Registered...Please Login :)");
		}
		
	}
	
	public void newConnection(CustomerNewConnection customerNewConnection)
	{
		try {
		custNewRepository.save(customerNewConnection);
		}
		catch(Exception e) {
			throw new InputException("Please fill all the details....");
		}
		
	}
	
	public CustomerGasBooking gasBooking(CustomerGasBooking customerGasBooking) 
	{
		return custGasRepository.save(customerGasBooking);
	}
	
	public CustomerAccessoriesBooking accessoriesBooking(CustomerAccessoriesBooking customerAccessoriesBooking)
	{
		return custAccessoriesRepository.save(customerAccessoriesBooking);
	}
	
	public void customerLogin(Map<String,String> loginData)
	{
		String pancardNumber=loginData.get("pancardNumber");
		String password=loginData.get("password");
		Boolean cust_found= false;
		if(pancardNumber==null || password==null)
		 {
			 throw new InputException("Please enter Pancard Number and Password");
		 }
		else {
		List<Customer> customers=(List<Customer>)customerRepository.findAll();
			for(Customer cust : customers)
			{
				if((cust.getPancardNumber().equals(pancardNumber)) && (cust.getPassword().equals(password)))
				{
					cust_found=true;
					break;
				}
			}
			if(cust_found==false)
			{
				throw new NotFoundException(".....LOGIN FAILED.....If you are new customer do register first..");
			}
		}
	}
	
	
	public Customer updateCustomer(Map<String,String> updateData)
	{
		Customer customer=new Customer();
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
		customer=customerRepository.findByPancardNumber(pancardNumber);
		
		if(!StringUtils.isEmpty(updatedFname))
		{
			customer.setFname(updatedFname);
		}
		if(!StringUtils.isEmpty(updatedLname))
		{
			customer.setLname(updatedLname);
		}
		if(!StringUtils.isEmpty(updatedEmail))
		{
			customer.setEmail(updatedEmail);
		}
		if(!StringUtils.isEmpty(updatedContact))
		{
			customer.setContactNumber(updatedContact);
		}
		if(!StringUtils.isEmpty(updatedPassword) && !StringUtils.isEmpty(updatedConfirmPassword))
		{
			if(!(updatedPassword.equals(updatedConfirmPassword)))
			{
				throw new InputException("Password and Confirm Password not Matching");
			}
			customer.setPassword(updatedPassword);
			customer.setConfirmPassword(updatedConfirmPassword);
		}
		else if(!StringUtils.isEmpty(updatedPassword) && StringUtils.isEmpty(updatedConfirmPassword))
		{
			throw new InputException("Please Provide Confirm Password...");
		}
		else if(StringUtils.isEmpty(updatedPassword) && !StringUtils.isEmpty(updatedConfirmPassword))
		{
			throw new InputException("Please Provide Password...");
		}
		
		customerRepository.save(customer);
	    }
		catch(NotFoundException e)
		{
			throw new NotFoundException("Pancard Number not Found :( ... Please Enter Valid Pancard Number!");
		}
		return customer;	
	}

	@Override
	public void addCustomerNewConnection(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	 
}

