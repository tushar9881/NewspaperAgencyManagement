package com.newspaper.dao;

import java.time.LocalDate;
import java.util.List;

//Class created to transfer data between client and server
public class CustomerDAO {
	
	private Integer userId;
	private String userName;
	private String fullName;
	private String email;
	private String contactNumber;
	private String fullAddress;
	private String sectorZone;
	private LocalDate registrationDate;
    private List<SubscriptionDAO> subscriptions;
    
    


    public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDAO(Integer userId, String userName, String fullName, String email, String contactNumber,
			String fullAddress, String sectorZone, LocalDate registrationDate, List<SubscriptionDAO> subscriptions) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.fullAddress = fullAddress;
		this.sectorZone = sectorZone;
		this.registrationDate = registrationDate;
		this.subscriptions = subscriptions;
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

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getSectorZone() {
		return sectorZone;
	}

	public void setSectorZone(String sectorZone) {
		this.sectorZone = sectorZone;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<SubscriptionDAO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDAO> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}
