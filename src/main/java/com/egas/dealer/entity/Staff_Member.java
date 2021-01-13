package com.egas.dealer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Staff_Member {

	@Id
	@GeneratedValue
	@Column(name = "staffId")
	private Integer staffId;
	
	@NotBlank(message="Pancard Number is mandatory")
	@Column(name = "pancardNumber",unique = true)
	@Pattern(regexp="^[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "length must be 10")
	private String pancardNumber;

	@NotBlank(message="First Name is mandatory")
	@Column(name = "fname")
	@Pattern(regexp="^[A-Z][a-z]{3,15}",message = "length must be in between 3 to 15")
	public String fname;
	
	@NotBlank(message="Last Name is mandatory")
	@Column(name = "lname")
	@Pattern(regexp="^[A-Z][a-z]{3,15}",message = "length must be in between 3 to 15")
	public String lname;
	
	@NotBlank(message="Gender is mandatory")
	@Column(name = "gender")
	@Pattern(regexp="^male|^female|^Male|^Female$",message="gender is not valid")
	public String gender;
	
	@NotBlank(message="City is mandatory")
	@Size(min = 3,max = 20)
	@Column(name = "city")
	public String city;
	
	@NotBlank(message="Enter password")
	@Column(name = "password")
	//@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	public String password;
	
	@NotBlank(message="Email is mandatory")
	@Column(name = "email")
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+$",message = "enter email in valid format")
	public String email;
	
	@NotBlank(message="Need to enter contact details")
	@Column(name = "contact")
	@Pattern(regexp="^[7-9][0-9]{9}$",message = "enter 10 digit mobile number")
	public String contact;
	
	@Column(name = "status")
	public String status="Pending";
	
	public Staff_Member() {
		// TODO Auto-generated constructor stub
	}
	public Staff_Member(String pancardNumber, String fname, String lname, String gender, String city, String password,
			String email, String contact,String status) {
		
		super();
		//this.staffId-=staffId;
		this.pancardNumber = pancardNumber;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.city = city;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.status=status;
	
	}
	
	public Staff_Member(String pancardNumber, String fname, String lname, String gender, String city, String password,
			String email, String contact) {
		
		super();
		this.pancardNumber = pancardNumber;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.city = city;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
	
	public Staff_Member(String pancardNumber,String password)
	{
		super();
		this.pancardNumber=pancardNumber;
		this.password=password;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		
		return "pancardNumber=" + pancardNumber + ",fname=" + fname + ", lname=" + lname + ", gender="
				+ gender + ", city=" + city + ", password=" + password + ", email=" + email + ", contact=" + contact +"status"+status;
	}
	
	
	
}
