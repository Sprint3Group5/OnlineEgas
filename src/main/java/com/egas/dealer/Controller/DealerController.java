package com.egas.dealer.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.exception.InputException;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.service.IDealerServiceImpl;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/dealers")
public class DealerController {

	@Autowired
	private IDealerServiceImpl dealerService;
	
	public String pancardNumberLogin;
	
	//********Dealer****************
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerDealer(@Valid @RequestBody Dealer dealer)
	{
		String password=dealer.getPassword();
		String confirmPassword=dealer.getConfirmPassword();
		if(!(password.equals(confirmPassword)))
		{
			throw new InputException("Password and Confirm Password not Matching");
		}
		try
		{	
			
			dealerService.addDealer(dealer);
			Integer dealerId=dealer.getDealerId();
			return new ResponseEntity<String>("Dealer With ID :"+dealerId+" Created Successfully ",HttpStatus.OK);	
		}
		catch(DataIntegrityViolationException e)
		{
			throw new InputException("The Username is already Registered...You can Login :)");
		}
	
	}
     
	@PostMapping(value="/login")
	public ResponseEntity<String> dealerLogin(@RequestBody Map<String,String> loginData) {
		System.out.println(loginData);
		pancardNumberLogin =dealerService.dealerLogin(loginData);
		System.out.println(pancardNumberLogin);
		return new ResponseEntity<String>("Login Successfull",HttpStatus.OK);
	}

	
	@PatchMapping(value = "/updateDealer")
	public Dealer updateDealer(@RequestBody Map<String,String> updateData)
	{
		if(pancardNumberLogin==null)
		{
			throw new NotFoundException("Dear Dealer Please Login First!");
		}
		Dealer data=dealerService.updateDealer(updateData);
		return data;
	}
	
	@GetMapping(value="/viewDealers")
	public ResponseEntity<List<Dealer>> viewDealers()
	{
		List<Dealer> data=dealerService.getAllDealers();
		return new ResponseEntity<List<Dealer>>(data,HttpStatus.OK);
	}
	
	//**********Customer Connection**************
	@GetMapping(value="/viewCustomerConnections")
	public ResponseEntity<List<CustomerNewConnection>> viewCustConections()
	{
		if(pancardNumberLogin==null)
		{
			throw new NotFoundException("Dear Dealer Please Login First!");
		}
		else
		{
		List<CustomerNewConnection> customerConnection=dealerService.getAllCustomerConnections();
		return new ResponseEntity<List<CustomerNewConnection>>(customerConnection,HttpStatus.OK);
		}
	} 
 
	@GetMapping(value="/viewPendingConnections")
	public ResponseEntity<List<CustomerNewConnection>> viewPendingConections()
	{
		if(pancardNumberLogin==null)
		{
			throw new NotFoundException("Dear Dealer Please Login First!");
		}
		else
		{
		List<CustomerNewConnection> customerConnection=dealerService.getPendingConnections();
		return new ResponseEntity<List<CustomerNewConnection>>(customerConnection,HttpStatus.OK);
		}
	} 
	@PatchMapping(value = "/changeConnectionStatus")
	public ResponseEntity<String> changeConnectionStatus(@RequestBody Map<String,String> updateStatus)
	{
		System.out.println(updateStatus);
		CustomerNewConnection data=dealerService.saveConnectionStatus(updateStatus);
		if(data!=null)
		{
		String status=updateStatus.get("custConnectionStatus");
		Integer custId=data.getCustomerId();
		return new ResponseEntity<String>("Customer Status With ID :"+custId+" updated to status:"+status+" Successfully :) ",HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Pancard Number not Found :( ... Please Enter Valid Pancard Number!");
		}
		
	}
	//*****Customer Gas Booking******
	  @GetMapping(value="/getAllGasBookings")
		public ResponseEntity<List<CustomerGasBooking>> getAllCustomerGasBookings()
		{
			List<CustomerGasBooking> data=dealerService.getAllCustomerBookings();
			return new ResponseEntity<List<CustomerGasBooking>>(data,HttpStatus.OK);
		}
		
	  @PutMapping(value = "/updateGasBookings")
	  @ResponseStatus(HttpStatus.OK)
		 public CustomerGasBooking updateGasBookingStatus(@RequestBody Map<String,String> custGasBooking)
		 {
		  CustomerGasBooking status_updated = dealerService.saveGasBookingStatus(custGasBooking);
		    return status_updated;
		 }
		
	 //*****Customer Accessories******
	 @GetMapping(value="/getAllCustomerAccessories")
		public ResponseEntity<List< CustomerAccessoriesBooking>> getAllCustomerAccessories()
		{
			List< CustomerAccessoriesBooking> data=dealerService.getAllCustomerAccessories();
			return new ResponseEntity<List< CustomerAccessoriesBooking>>(data,HttpStatus.OK);
		}
		
		 @PutMapping(value = "/updateAccessories")
		 @ResponseStatus(HttpStatus.OK)
		 public CustomerAccessoriesBooking updateAccessoriesStatus(@RequestBody Map<String,String> custAccessories)
		 {
			 CustomerAccessoriesBooking status_updated = dealerService.saveAccessoriesStatus(custAccessories);
		        return status_updated;
		 }

	 //*****Delivery Staff Methods******
	  @GetMapping(value="/getAllDeliveryStaff")
			public ResponseEntity<List<Staff_Member>> getAllDeliveryStaff()
			{
				List<Staff_Member> data=dealerService.getAllDeliveryStaff();
				return new ResponseEntity<List<Staff_Member>>(data,HttpStatus.OK);
			}
			
	  @PutMapping(value = "/updatestaff")
	  @ResponseStatus(HttpStatus.OK)
		    public Staff_Member updateDeliveryStaffStatus(@RequestBody Map<String,String> deliveryStaff)
			{
			     Staff_Member status_updated = dealerService.saveStaffStatus(deliveryStaff);
			     return status_updated ;
			}
	  
	
	
	
	
}
