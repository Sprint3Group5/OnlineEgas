package com.egas.dealer.Controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.service.ICustomerServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private ICustomerServiceImpl customerService;
	
	public String pancardNumberLogin;
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer)
	{
		
		String password=customer.getPassword();
		String confirmPassword=customer.getConfirmPassword();
		if(!(password.equals(confirmPassword)))
		{
			throw new InputException("Password and Confirm Password not Matching");
		}
		try
		{	
			
			customerService.addCustomer(customer);
			Integer customerId=customer.getCustomerId();
			return new ResponseEntity<String>("Customer With ID :"+customerId+" Created Successfully ",HttpStatus.OK);	
		}
		catch(DataIntegrityViolationException e)
		{
			throw new InputException("The Username is already Registered...You can Login :)");
		}
		
	}
	
	@GetMapping(value="/login/{pancard}/{pass}")
	public ResponseEntity<String> customerLogin(@PathVariable String pancard,@PathVariable String pass)
	{
		pancardNumberLogin = customerService.customerLogin(pancard, pass);
		System.out.println(pancardNumberLogin);
		return new ResponseEntity<String>("...LOGIN SUCCESSFUL...",HttpStatus.OK);
	}
	
	@GetMapping(value="/getCustomerName/{pancardnumber}")
	public String getCustomerName(@PathVariable String pancardnumber)
	{
		String customerName=customerService.getCustomerName(pancardnumber);
		return customerName;
	}
	

	
	@PostMapping("/newConnection")
	public ResponseEntity<Void> newConnection(@RequestBody CustomerNewConnection newConnection)
	{
		 customerService.newConnection(newConnection);
		//return data;
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/gasBooking")
	public CustomerGasBooking gasBooking(@RequestBody CustomerGasBooking gasBooking)
	{
		CustomerGasBooking data=customerService.gasBooking(gasBooking);
		//return data;
		return data;
	}
	
	@PostMapping("/accessoriesBooking")
	public CustomerAccessoriesBooking accessoriesBooking(@RequestBody CustomerAccessoriesBooking accessoriesBooking)
	{
		CustomerAccessoriesBooking data=customerService.accessoriesBooking(accessoriesBooking);
		//return data;
		return data;
	}
	
	
	@PatchMapping(value = "/updateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody Map<String,String> updateData)
	{
		if(pancardNumberLogin == null)
		{
			throw new NotFoundException("Dear Customer Please login first");
		}
		Customer data=customerService.updateCustomer(updateData);
		return new ResponseEntity<String>("Updated Successfully :)",HttpStatus.OK);
	}
	
	
}
