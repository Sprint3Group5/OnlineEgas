package com.egas.dealer.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_Gas_Booking")
public class CustomerGasBooking {
	@Id
	@GeneratedValue
	Integer custGasBookingId;
	private String custPancard;
	public String custName;
	public String custAddress;
	public String custCity;
	public String custState;
	public String custContact;
	public String accessory="Gas Cylinder";
	public String custGasBookingStatus="Pending";
	public String custGasDeliveryStatus="Not Delivered";
	
	
	public CustomerGasBooking() {
		super();
	}


	public CustomerGasBooking(String custPancard, String custName, String custAddress,
			String custCity, String custState, String custContact,String accessory, String custBookingStatus, String custGasDeliveryStatus) {
		super();
		
		this.custPancard = custPancard;
		this.custName = custName;
		this.custAddress = custAddress;
		this.custCity = custCity;
		this.custState = custState;
		this.custContact = custContact;
		this.accessory= accessory;
		this.custGasBookingStatus = custBookingStatus;
		this.custGasDeliveryStatus = custGasDeliveryStatus;
	}


	public Integer getCustGasBookingId() {
		return custGasBookingId;
	}


	public void setCustGasBookingId(Integer custGasBookingId) {
		this.custGasBookingId = custGasBookingId;
	}


	public String getCustPancard() {
		return custPancard;
	}


	public void setCustPancard(String custPancard) {
		this.custPancard = custPancard;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getCustAddress() {
		return custAddress;
	}


	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}


	public String getCustCity() {
		return custCity;
	}


	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}


	public String getCustState() {
		return custState;
	}


	public void setCustState(String custState) {
		this.custState = custState;
	}


	public String getCustContact() {
		return custContact;
	}


	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}


	public String getCustGasBookingStatus() {
		return custGasBookingStatus;
	}


	public void setCustGasBookingStatus(String custGasBookingStatus) {
		this.custGasBookingStatus = custGasBookingStatus;
	}


	public String getCustGasDeliveryStatus() {
		return custGasDeliveryStatus;
	}


	public void setCustGasDeliveryStatus(String custGasDeliveryStatus) {
		this.custGasDeliveryStatus = custGasDeliveryStatus;
	}


	public String getAccessory() {
		return accessory;
	}


	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	
}
