package com.newspaper.dao;

public class UserDAOforLOGIN {
	
	private String userName;
	private String password;
	private String role;
	private Integer userId;
	
	public UserDAOforLOGIN() {
		// TODO Auto-generated constructor stub
	}

	
	public UserDAOforLOGIN(String userName, String password, String role, Integer userId) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.userId = userId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
