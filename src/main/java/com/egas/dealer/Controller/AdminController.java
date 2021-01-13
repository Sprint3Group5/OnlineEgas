package com.egas.dealer.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egas.dealer.entity.Admin;
import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.service.AdminService;





@RestController
@RequestMapping(value="/admin")
public class AdminController{
	 
@Autowired
private AdminService adminService;


@PostMapping("/saveAdmin")
public Admin create(@RequestBody Admin admin)
{
	return adminService.save(admin);
}	



@PatchMapping (value="/login")
public ResponseEntity<String> adminLogin(@Valid @RequestBody Map<String,String> loginData)
{
String result=adminService.adminLogin(loginData);
return new ResponseEntity<String>(""+result,HttpStatus.OK);
	
}



@PatchMapping(value="/updatePassword")
public void  updateAdminPassword(@RequestBody Map<String,String> updateData )
{
	  Admin admin=adminService.updateAdmin(updateData);
	 
}

@GetMapping(value="/getDealers")
public ResponseEntity<List<Dealer>> getDealers()
{
	List<Dealer> data=adminService.getAllDealers();
	return new ResponseEntity<List<Dealer>>(data,HttpStatus.OK);
}

@GetMapping(value="/getCustomers")
public ResponseEntity<List<Customer>> getCustomers()
{
	List<Customer> data=adminService.getAllCustomers();
	return new ResponseEntity<List<Customer>>(data,HttpStatus.OK);
}


@GetMapping(value="/getDeliveryStaff")
public ResponseEntity<List<Staff_Member>> getDeliveryStaff()
{
	List< Staff_Member> data=adminService.getAllDeliveryStaff();
	return new ResponseEntity<List< Staff_Member>>(data,HttpStatus.OK);
}



@GetMapping(value = "/getCustomerGasBooking")
public ResponseEntity<List<CustomerGasBooking>> viewCustGasBooking()
{
	List<CustomerGasBooking> data=adminService.getAllCustomerGasBooking();
	return new ResponseEntity<List<CustomerGasBooking>>(data,HttpStatus.OK);
}



@GetMapping(value = "/getCustomerAccessoriesBooking")
public ResponseEntity<List<CustomerAccessoriesBooking>>  viewCustAccessoriesBooking()
{
	 List<CustomerAccessoriesBooking> data =adminService.getAllCustomerAccessoriesBooking();
	 return new ResponseEntity<List<CustomerAccessoriesBooking>>(data,HttpStatus.OK);
}


	
}





 