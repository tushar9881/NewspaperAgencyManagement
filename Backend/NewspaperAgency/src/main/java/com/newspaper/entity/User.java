package com.newspaper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role",discriminatorType = DiscriminatorType.STRING)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String contactNumber;
	@Column(insertable = false, updatable = false)
	private String role;

	@Version
	private Integer version;  // << Add this version field
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String userName, String password, String fullName, String email, String contactNumber) {
		super();
		userId = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
	}
	
	

	public User(Integer userId, String userName, String fullName, String email, String contactNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public String getRole() {
	    return role;
	}
	
	public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
	public Integer getId() {
		return userId;
	}

	public void setId(Integer id) {
		userId = id;
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

	
	@Override
	public String toString() {
	    return "User [userId=" + userId + ", userName=" + userName + ", fullName=" + fullName
	            + ", email=" + email + ", contactNumber=" + contactNumber + "]";
	}

	
	
}
