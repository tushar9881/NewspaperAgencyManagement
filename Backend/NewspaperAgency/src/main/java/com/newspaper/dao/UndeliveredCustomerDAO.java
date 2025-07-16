package com.newspaper.dao;

public class UndeliveredCustomerDAO {

	private Integer userId;
	private String userName;
	private String fullName;
	private String fullAddress;
	private String sectorZone;
	
	public UndeliveredCustomerDAO() {
		// TODO Auto-generated constructor stub
	}

	public UndeliveredCustomerDAO(Integer userId, String userName, String fullName, String fullAddress,
			String sectorZone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.fullAddress = fullAddress;
		this.sectorZone = sectorZone;
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
	
	
}
