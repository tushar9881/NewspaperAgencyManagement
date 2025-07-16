package com.newspaper.dao;

public class PasswordChangeRequest {
	
	private String oldPassword;
	private String newPassword;
	
	public PasswordChangeRequest() {
		// TODO Auto-generated constructor stub
	}

	public PasswordChangeRequest(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordChangeRequest [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
	
	
}
