package com.newspaper.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@DiscriminatorValue("admin")
@Table(name="admin")
public class Admin extends User{

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer id, String userName, String password, String fullName, String email, String contactNumber) {
		super(id, userName, password, fullName, email, contactNumber);
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer id, String userName, String fullName, String email, String contactNumber) {
		super(id, userName, fullName, email, contactNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [getId()=" + getId() + ", getUserName()=" + getUserName() + ", getPassword()=" + getPassword()
				+ ", getFullName()=" + getFullName() + ", getEmail()=" + getEmail() + ", getContactNumber()="
				+ getContactNumber() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
}
