package com.newspaper.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.newspaper.entity.Subscription;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@DiscriminatorValue("customer")
@Table(name = "customer")
public class Customer extends User {

	private String fullAddress;
	private String sectorZone;
	private LocalDate registrationDate;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Subscription> subscriptions = new ArrayList<>();
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String fullAddress, String sectorZone, LocalDate registrationDate,
			List<Subscription> subscriptions) {
		super();
		this.fullAddress = fullAddress;
		this.sectorZone = sectorZone;
		this.registrationDate = registrationDate;
		this.subscriptions = subscriptions;
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

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "Customer [fullAddress=" + fullAddress + ", sectorZone=" + sectorZone + ", registrationDate="
				+ registrationDate + ", subscription=" + subscriptions + "]";
	}

	
}
