package com.newspaper.dao;

import com.newspaper.entity.DailyDeliveryLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryExecutiveDAO {

	private Integer userId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String contactNumber;
	private String role;
	private String zoneAssigned;
	
	public DeliveryExecutiveDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DeliveryExecutiveDAO(Integer userId, String userName, String password, String fullName, String email,
			String contactNumber, String role, String zoneAssigned) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.zoneAssigned = zoneAssigned;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getZoneAssigned() {
		return zoneAssigned;
	}


	public void setZoneAssigned(String zoneAssigned) {
		this.zoneAssigned = zoneAssigned;
	}
	
	
	
}
