package com.egas.dealer.Controller;

import java.util.List;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.exception.NotFoundException;
import com.egas.dealer.repository.StaffMemberRepository;
import com.egas.dealer.service.StaffMemberServiceImpl;


@CrossOrigin
@RestController
@RequestMapping(value="/staffmembers")
public class StaffMemberController {

	
	@Autowired
	private StaffMemberServiceImpl staffMemberService;
	
	public String pancardNumberLogin;
	
	@GetMapping(value="/getAllStaffMembers")
	public ResponseEntity<List<Staff_Member>> getStaffMembers()
	{
		List<Staff_Member> staffmembers=staffMemberService.getAllStaffMember();
		return new ResponseEntity<List<Staff_Member>>(staffmembers,HttpStatus.OK);
	}
	
	@PostMapping(value ="/register")
	public ResponseEntity<String> registerStaffMember(@Valid @RequestBody Staff_Member staffmember)
	{
		staffMemberService.saveStaffMember(staffmember);
		Integer staffId=staffmember.getStaffId();
		return new ResponseEntity<String>(" StaffMember with Id: " + staffId + " create Successfully ",HttpStatus.OK);
    }
	
	@PatchMapping(value = "/updateStaffMember")
	public ResponseEntity<String> updateStaffMember(@RequestBody Map<String,String> updateData)
	{
		if(pancardNumberLogin == null)
		{
			throw new NotFoundException("Dear StaffDelivery Please login first");
		}
		Staff_Member staff=staffMemberService.updateStaffMember(updateData);
		return new ResponseEntity<String>("Updated Successfully :)",HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/stafflogin/{pancard}/{pass}")
	public ResponseEntity<String> staffLogin(@PathVariable String pancard, @PathVariable String pass)
	{
		pancardNumberLogin = staffMemberService.staffLogin(pancard, pass);
		System.out.println(pancardNumberLogin);
		return new ResponseEntity<String>("Login Successful",HttpStatus.OK);
	}
	@GetMapping(value = "/getCustomerGasBooking")
	public ResponseEntity<List<CustomerGasBooking>> viewCustGasBooking()
	{
		if(pancardNumberLogin==null)
		{
			throw new NotFoundException("Dear StaffDelivery Please Login First!");
		}
		else {
		List<CustomerGasBooking> customerGasBooking=staffMemberService.getAllCustomerGasBooking();
		return new ResponseEntity<List<CustomerGasBooking>>(customerGasBooking,HttpStatus.OK);
	}
	}
	 
	 @PatchMapping(value = "/updateGasDeliveryStatus")
	 public ResponseEntity<String> updateGasDeliveryStatusBasedOnId(@RequestBody Map<String,String> updateStatus)
	 {
		CustomerGasBooking customerGas=staffMemberService.updateGasDeliveryStatus(updateStatus);
		if(customerGas != null)
		{
		String status=updateStatus.get("custGasDeliveryStatus");
		Integer custId=customerGas.getCustGasBookingId();
	    return new ResponseEntity<String>("Customer Status with Id: " + custId + " updated to status: " + status + " Successfully ",HttpStatus.OK);
	 }else {
		 throw new NotFoundException("Pancard Number Not Found:(");
	 }
	 }
	 @GetMapping(value="/viewPendingGasBooking")
		public ResponseEntity<List<CustomerGasBooking>> viewPendingGasBooking()
		{
			if(pancardNumberLogin==null)
			{
				throw new NotFoundException("Dear StaffDelivery Please Login First!");
			}
			else
			{
			List<CustomerGasBooking> customerGas=staffMemberService.getPendingGasBooking();
			return new ResponseEntity<List<CustomerGasBooking>>(customerGas,HttpStatus.OK);
			}
		} 
	 
	 @GetMapping(value = "/getCustomerAccessoriesBooking")
	 public ResponseEntity<List<CustomerAccessoriesBooking>>  viewCustAccessoriesBooking()
	 {
		 if(pancardNumberLogin==null)
			{
				throw new NotFoundException("Dear StaffDelivery Please Login First!");
			}
		 List<CustomerAccessoriesBooking> customerAccessoriesBooking =staffMemberService.getAllCustomerAccessoriesBooking();
		 return new ResponseEntity<List<CustomerAccessoriesBooking>>(customerAccessoriesBooking,HttpStatus.OK);
	 }
	 @GetMapping(value="/viewPendingAccessoriesBooking")
		public ResponseEntity<List<CustomerAccessoriesBooking>> viewPendingAccessoriesBooking()
		{
			if(pancardNumberLogin==null)
			{
				throw new NotFoundException("Dear StaffDelivery Please Login First!");
			}
			else
			{
			List<CustomerAccessoriesBooking> customerAccessories=staffMemberService.getPendingAccessoriesBooking();
			return new ResponseEntity<List<CustomerAccessoriesBooking>>(customerAccessories,HttpStatus.OK);
			}
		} 
	 
	 
	 @PatchMapping(value= "/updateAccessoriesDeliveryStatus")
	 public ResponseEntity<String> updateAccessDeliveryStatus(@RequestBody Map<String,String> updateData)
	 {
		 CustomerAccessoriesBooking custAccessories=staffMemberService.updateAccessoriesDeliveryStatus(updateData);
		 if(custAccessories != null) {
			 String status=updateData.get("custAccessoriesDeliveryStatus");
			 Integer custId=custAccessories.getCustomerId();
			 return new ResponseEntity<String>("Customer Status with Id: " + custId + " updated to status: " + status + " Successfully ",HttpStatus.OK);
		 }else {
			 throw new NotFoundException("Pancard Number not found:(");
		 }
	 }
}
