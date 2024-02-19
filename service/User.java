package in.ineuron.service;

import java.time.LocalDate;
import java.util.Date;

public class User {
	String username;
	String userpassword;
	String email;
	LocalDate dob;
	String address;
	
	
	public User(String username, String userpassword, String email, LocalDate dob, String address) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.email = email;
		this.dob = dob;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
