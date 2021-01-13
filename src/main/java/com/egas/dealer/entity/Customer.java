package com.egas.dealer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Customer {


	@Id
	@GeneratedValue
	Integer customerId;
	@NotBlank(message = "Pancard Number is required")
	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]",message="Enter Valid Pancard Number")
	@Column(unique=true)
	private String pancardNumber;
	@NotBlank(message = "First Name is required")
	@Size(min=3,message = "Please Enter Valid First Name")
	String fname;
	@NotBlank(message = "Last Name is required")
	@Size(min=3,message = "Please Enter Valid Last Name")
	String lname;
	@NotBlank(message = "Gender is required")
	String gender;
	@NotBlank(message = "Email is required")
	@Email
	String email;
	@NotBlank(message = "Contact Number is required")
	@Pattern(regexp="[789][0-9]{9}",message="Enter Valid Number")
	String contactNumber;
	@NotBlank(message = "Password is required")
	@Size(min=3,message = "Password Entered is weak :(...Please Enter Strong Password")
	String password;
	@NotBlank(message = "Confirm Password is required")
	String confirmPassword;


	public Customer()
	{
		
	}
	
	public Customer(String pancardNumber, String fname, String lname, String gender, String email, String contactNumber,
			String password, String confirmPassword) {
		super();
		this.pancardNumber = pancardNumber;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer dealerId) {
		this.customerId = dealerId;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	@Override
	public String toString() {
		return "pancardNumber=" + pancardNumber + ", fname=" + fname + ", lname=" + lname + ", gender="
				+ gender + ", email=" + email + ", contactNumber=" + contactNumber + ", password=" + password
				+ ", confirmPassword=" + confirmPassword;
	}
	
	
}
