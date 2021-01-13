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
public class Dealer {


	@Id
	@GeneratedValue
	Integer dealerId;
	@NotBlank(message = "Pancard Number is required")
	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]",message="Enter Valid Pancard Number")
	@Column(unique=true)
	private String pancardNumber;
	@NotBlank(message = "First Name is required")
	@Size(min=3,message = "Please Enter Valid First Name")
	String firstName;
	@NotBlank(message = "Last Name is required")
	@Size(min=3,message = "Please Enter Valid Last Name")
	String lastName;
	@NotBlank(message = "Gender is required")
	String gender;
	//@Pattern(regexp="[mfMF]{1}",message="Enter Valid Gender...Enter Female or Male")
	@NotBlank(message = "Email is required")
	@Email
	String email;
	@NotBlank(message = "Contact Number is required")
	@Pattern(regexp="[789][0-9]{9}",message="Enter Valid Number")
	String contactNumber;
	@NotBlank(message = "Password is required")
	@Size(min=5,message = "Password Entered is weak :(...Please Enter Strong Password")
	String password;
	@NotBlank(message = "Confirm Password is required")
	String confirmPassword;
	@NotBlank(message = "City is required")
	@Size(min=3,message = "Enter Valid City")
	String city;
	@NotBlank(message = "State is required")
	@Size(min=3,message = "Enter Valid State")
	String state;
	String dealerStatus="Pending";
	
	
	public Dealer(String pancardNumber, String firstName, String lastName, String gender, String email,
		String contactNumber, String password, String confirmPassword, String city, String state) {
	super();
	//this.dealerId=dealerId;
	this.pancardNumber = pancardNumber;
	this.firstName = firstName;
	this.lastName = lastName;
	this.gender = gender;
	this.email = email;
	this.contactNumber = contactNumber;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.city = city;
	this.state = state;
}
	
	public Dealer()
	{
		
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	
	public String getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getDealerStatus() {
		return dealerStatus;
	}

	public void setDealerStatus(String dealerStatus) {
		this.dealerStatus = dealerStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Dealer [dealerId=" + dealerId + ", pancardNumber=" + pancardNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + ", contactNumber="
				+ contactNumber + ", password=" + password + ", confirmPassword=" + confirmPassword + ", city=" + city
				+ ", state=" + state + ", dealerStatus=" + dealerStatus + "]";
	}
	
}
