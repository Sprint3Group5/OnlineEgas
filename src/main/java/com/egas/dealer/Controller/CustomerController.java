package com.egas.dealer.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.service.ICustomerServiceImpl;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private ICustomerServiceImpl customerService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCustomer(@Validated @RequestBody Customer customer)
	{
		
		customerService.addCustomer(customer);
		Integer customerId=customer.getCustomerId();
		return new ResponseEntity<String>("Customer With ID "+customerId+" Created and Registered Successfully ",HttpStatus.OK);
		
	}
	
	@PatchMapping(value="/login")
	public ResponseEntity<String> customerLogin(@RequestBody Map<String,String> loginData)
	{
		customerService.customerLogin(loginData);
		return new ResponseEntity<String>("...LOGIN SUCCESSFUL...",HttpStatus.OK);
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
	public Customer updateCustomer(@RequestBody Map<String,String> updateData)
	{
		
		Customer data=customerService.updateCustomer(updateData);
		return data;
	}
	
}
