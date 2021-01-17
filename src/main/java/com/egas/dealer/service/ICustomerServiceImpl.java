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

	static String pancardNumberLogin;
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
	
	public CustomerNewConnection newConnection(CustomerNewConnection customerNewConnection)
	{
		return custNewRepository.save(customerNewConnection);
		
	}
	
	public CustomerGasBooking gasBooking(CustomerGasBooking customerGasBooking) 
	{
		return custGasRepository.save(customerGasBooking);
	}
	
	public CustomerAccessoriesBooking accessoriesBooking(CustomerAccessoriesBooking customerAccessoriesBooking)
	{
		return custAccessoriesRepository.save(customerAccessoriesBooking);
	}
	
	public String customerLogin(String pancard,String pass)
	{
		pancardNumberLogin = pancard;
		String password = pass;
		Customer customer=customerRepository.findByPancardNumber(pancardNumberLogin);
		Boolean cust_found= false;
		if(customer==null)
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
		List<Customer> cust=(List<Customer>) customerRepository.findAll();
		for(Customer s:cust)
		{
			if(s.getPancardNumber().equals(pancardNumberLogin) && s.getPassword().equals(password))
			{
				cust_found=true;
				return pancardNumberLogin;
			}
		}
		if(cust_found==false)
		{
			throw new NotFoundException("Login Fail...If you are new user do register first");
		}
		return pancardNumberLogin;
	}
	
	public String getCustomerName(String pancardnumber)
	{
		String customerName="";
		List<Customer> cust=(List<Customer>) customerRepository.findAll();
		for(Customer s:cust)
		{
			if(s.getPancardNumber().equals(pancardnumber))
			{
				customerName=s.getFname()+' '+s.getLname();
			}
		}
		return customerName;
	}
	
	
	public Customer updateCustomer(Map<String,String> updateData)
	{
		Customer customer = new Customer();
		String pancardNumber=pancardNumberLogin;
		String updatedFname=updateData.get("fname");
		String updatedLname=updateData.get("lname");
		String updatedContact=updateData.get("contactNumber");
		String updatedEmail=updateData.get("email");
		String updatedPassword=updateData.get("password");
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
			if(!StringUtils.isEmpty(updatedContact))
			{
				customer.setContactNumber(updatedContact);
			}
			if(!StringUtils.isEmpty(updatedEmail))
			{
				customer.setEmail(updatedEmail);
			}
			if(!StringUtils.isEmpty(updatedPassword))
			{
				customer.setPassword(updatedPassword);
			}
			customerRepository.save(customer);
		}
		catch(Exception ex)
		{
			throw new NotFoundException("Pancard Number not Found.....Please Enter valid Pancard Number.....");
		}
		return customer;	
	}

	@Override
	public void addCustomerNewConnection(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	 
}

