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
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.service.StaffMemberServiceImpl;


@CrossOrigin
@RestController
@RequestMapping(value="/staffmembers")
public class StaffMemberController {

	@Autowired
	private StaffMemberServiceImpl staffMemberService;
	
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
	
	@PutMapping(value = "/updateStaffMember")
	public Staff_Member updateStaffMember(@RequestBody Map<String,String> updateData)
	{
		Staff_Member staff=staffMemberService.updateStaffMember(updateData);
		return staff;
	}
	
	@PostMapping(value="/staffMemberLogin/login")
	public ResponseEntity<String> loginStaffMember(@RequestBody Staff_Member staffMember)
	{
		if(staffMemberService.staffMemberLogin(staffMember.getPancardNumber(), staffMember.getPassword()))
		{
			return new ResponseEntity<String>("Login Successful... :)",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Invalid PancardNumber or Password.......If you are a new user first register yourself.... :)",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getCustomerGasBooking")
	public ResponseEntity<List<CustomerGasBooking>> viewCustGasBooking()
	{
		List<CustomerGasBooking> customerGasBooking=staffMemberService.getAllCustomerGasBooking();
		return new ResponseEntity<List<CustomerGasBooking>>(customerGasBooking,HttpStatus.OK);
	}
	
	
	 @RequestMapping(value = "/custCity/{custCity}") 
	 public ResponseEntity<List<CustomerGasBooking>> findByCity(@PathVariable(value = "custCity") String custCity) 
	 {
	      List<CustomerGasBooking> gasBooking = staffMemberService.findByCustCity(custCity);
	      return new ResponseEntity<List<CustomerGasBooking>>(gasBooking, HttpStatus.OK);
	  
	 }
	 
	 @PatchMapping(value = "/updateGasDeliveryStatus")
	 public ResponseEntity<String> updateGasDeliveryStatusBasedOnId(@RequestBody Map<String,String> updateStatus)
	 {
		CustomerGasBooking customerGas=staffMemberService.updateGasDeliveryStatus(updateStatus);
		String status=updateStatus.get("custGasDeliveryStatus");
		Integer custId=customerGas.getCustGasBookingId();
	    return new ResponseEntity<String>("Customer Status with Id: " + custId + " updated to status: " + status + " Successfully ",HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/getCustomerAccessoriesBooking")
	 public ResponseEntity<List<CustomerAccessoriesBooking>>  viewCustAccessoriesBooking()
	 {
		 List<CustomerAccessoriesBooking> customerAccessoriesBooking =staffMemberService.getAllCustomerAccessoriesBooking();
		 return new ResponseEntity<List<CustomerAccessoriesBooking>>(customerAccessoriesBooking,HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/custBookingCity/{custBookingCity}")
	 public ResponseEntity<List<CustomerAccessoriesBooking>> findByCity1(@PathVariable(value = "custBookingCity") String custBookingCity)
	 {
		 List<CustomerAccessoriesBooking> accessoriesBooking = staffMemberService.findByCustBookingCity(custBookingCity);
		 return new ResponseEntity<List<CustomerAccessoriesBooking>>(accessoriesBooking,HttpStatus.OK);
	 }
	 
	 @PatchMapping(value= "updateAccessoriesDeliveryStatus")
	 public ResponseEntity<String> updateAccessDeliveryStatusBasedOnId(@RequestBody Map<String,String> updateStatus)
	 {
		 CustomerAccessoriesBooking customerAccessories=staffMemberService.updateAccessoriesDeliveryStatus(updateStatus);
		 String status=updateStatus.get("custAccessoriesDeliveryStatus");
		 Integer custId=customerAccessories.getCustomerId();
		 return new ResponseEntity<String>("Customer Status with Id: " + custId + " updated to status: " + status + " Successfully ",HttpStatus.OK);
	 }
	 
}
