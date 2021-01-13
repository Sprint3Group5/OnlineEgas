package com.egas.dealer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egas.dealer.entity.Admin;
import com.egas.dealer.entity.Customer;
import com.egas.dealer.entity.CustomerAccessoriesBooking;
import com.egas.dealer.entity.CustomerGasBooking;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.entity.Staff_Member;
import com.egas.dealer.repository.AdminRepository;
import com.egas.dealer.repository.CustomerAccessoriesBookingRepository;
import com.egas.dealer.repository.CustomerGasBookingRepository;
import com.egas.dealer.repository.CustomerRepository;
import com.egas.dealer.repository.DealerRepository;
import com.egas.dealer.repository.StaffMemberRepository;



@Service
public class AdminService{
	
	@Autowired
private	AdminRepository repository;

	@Autowired
private DealerRepository dealerRepository;
	public List<Dealer> getAllDealers()
	{
		List<Dealer> dealer=(List<Dealer>)dealerRepository.findAll();
		return dealer;
	}
	

	@Autowired
private	CustomerRepository customerRepository;
	public List<Customer> getAllCustomers()
	{
		List<Customer> customer=(List<Customer>)customerRepository.findAll();
		return customer;
	}

	@Autowired
	private StaffMemberRepository deliveryStaffrepository;
	public List<Staff_Member> getAllDeliveryStaff()
	{
		List<Staff_Member> deliveryStaff=(List<Staff_Member>)deliveryStaffrepository.findAll();
		return deliveryStaff;
	}
	
	
	@Autowired
	private CustomerGasBookingRepository custGasRepository;
	 public List<CustomerGasBooking> getAllCustomerGasBooking()
		{
			List<CustomerGasBooking> gasBooking=(List<CustomerGasBooking>)custGasRepository.findAll();
			return gasBooking;
		
		}
		

	@Autowired
	private CustomerAccessoriesBookingRepository custAccessoriesRepository;
	 public List<CustomerAccessoriesBooking> getAllCustomerAccessoriesBooking()
	 {
		 List<CustomerAccessoriesBooking> accessoriesBooking=(List<CustomerAccessoriesBooking>)custAccessoriesRepository.findAll();
		 return accessoriesBooking;
	 }
	

	
	
	public Admin save(Admin admin) {
		// TODO Auto-generated method stub
		
		return repository.save(admin);
	}

	
	public String  adminLogin(Map<String,String> loginData) {
		String result="";
		String username=loginData.get("username");
		String password=loginData.get("password");
				List<Admin> admin=(List<Admin>) repository.findAll();
				Boolean admin_found=false;
				for (Admin temp : admin) {
					if(temp.getUsername().equals(username) && temp.getPassword().equals(password))
							{
						System.out.println("....Login Succesful...");
						admin_found=true;
						result="....Login Succesful...";
						return result;
		//				break;
							}
				}
				if(admin_found==false) {
					System.out.println("....Invalid Credentails...");
					result="....Invalid Credentails...";
					return result;
				}
				return result;
	}
	
	
	public Admin updateAdmin(Map<String,String> updateData) {
		
		String username=updateData.get("username");
		
		String updatedPassword=updateData.get("password");
		return repository.findById(username).map(admin->
		        {
		           
		            if(!StringUtils.isEmpty(updatedPassword))
		            {
		                admin.setPassword(updatedPassword);
		            }
		repository.save(admin);
		            return admin;
		        }).orElseThrow();   
	 
	}
}
