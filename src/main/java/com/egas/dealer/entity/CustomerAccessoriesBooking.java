package com.egas.dealer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class CustomerAccessoriesBooking
{
	@Id
	@GeneratedValue
	private Integer customerId;
	@NotBlank(message = "Pancard Number is required")
	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]",message="Enter Valid Pancard Number")
	private String custPancard;
	@NotBlank(message = "First Name is required")
	@Size(min=3,message = "Please Enter Valid First Name")
	public String custFirstName;
	@NotBlank(message = "Last Name is required")
	@Size(min=3,message = "Please Enter Valid Last Name")
	public String custLastName;
	@NotBlank(message = "State is required")
	public String custState;
	@NotBlank(message = "City is required")
	public String custBookingCity;
	@NotBlank(message = "Address is required")
	public String custAddress;
	@NotBlank(message = "Contact Number is required")
	@Pattern(regexp="[789][0-9]{9}",message="Enter Valid Number")
	public String custContact;
	public String custAccessories;
	public String custAccessoriesBookingStatus="Pending";
	public String custAccessoriesDeliveryStatus="Not Delivered";
	
	
	public CustomerAccessoriesBooking() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerAccessoriesBooking(
			String custPancard,String custFirstName,String custLastName,String custState,String custBookingCity,
		    String custAddress,String custContact,String custAccessories, String custAccessoriesBookingStatus, String custAccessoriesDeliveryStatus) {
		super();
		
		this.custPancard = custPancard;
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.custState = custState;
		this.custBookingCity = custBookingCity;
		this.custAddress = custAddress;
		this.custContact = custContact;
		this.custAccessories = custAccessories;
		this.custAccessoriesBookingStatus = custAccessoriesBookingStatus;
		this.custAccessoriesDeliveryStatus = custAccessoriesDeliveryStatus;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer custAccessoriesId) {
		this.customerId = custAccessoriesId;
	}

	public String getCustPancard() {
		return custPancard;
	}

	public void setCustPancard(String custPancard) {
		this.custPancard = custPancard;
	}

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustBookingCity() {
		return custBookingCity;
	}

	public void setCustBookingCity(String custBookingCity) {
		this.custBookingCity = custBookingCity;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	public String getCustAccessories() {
		return custAccessories;
	}

	public void setCustAccessories(String custAccessories) {
		this.custAccessories = custAccessories;
	}

	public String getCustAccessoriesBookingStatus() {
		return custAccessoriesBookingStatus;
	}

	public void setCustAccessoriesBookingStatus(String custGasBookingStatus) {
		this.custAccessoriesBookingStatus = custGasBookingStatus;
	}

	public String getCustAccessoriesDeliveryStatus() {
		return custAccessoriesDeliveryStatus;
	}

	public void setCustAccessoriesDeliveryStatus(String custAccessoriesDeliveryStatus) {
		this.custAccessoriesDeliveryStatus = custAccessoriesDeliveryStatus;
	}

	
}
