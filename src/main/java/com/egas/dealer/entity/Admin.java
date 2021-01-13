package com.egas.dealer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import com.sun.istack.NotNull;

@Entity
public class Admin{

	@Id
	@NotNull
	//@Email
	@Size(min=6, message="Please enter valid Username")
	@Column(unique=true)
	
	private String username;
	
	@NotNull
	@Size(min=3,message = "Password Entered is weak :(...Please Enter Strong Password")
	String password;
	
	
	  
	public Admin() {
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + "]";
	}
	
	}
