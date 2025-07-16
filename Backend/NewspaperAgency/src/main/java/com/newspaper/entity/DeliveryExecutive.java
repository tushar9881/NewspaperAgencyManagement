package com.newspaper.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("delivery_executive")
@PrimaryKeyJoinColumn(name="userId")
@Table(name = "delivery_executive")
public class DeliveryExecutive extends User {

	private String zoneAssigned;

	@OneToMany(mappedBy = "deliveryExecutive", cascade = CascadeType.ALL)
	@JsonManagedReference("deReference")
	private List<DailyDeliveryLog> dailyDeliveryLog = new ArrayList<>();

	

	public DeliveryExecutive() {
		// TODO Auto-generated constructor stub
	}

	

	public DeliveryExecutive(Integer id, String userName, String password, String fullName, String email, String contactNumber,String zoneAssigned, List<DailyDeliveryLog> dailyDeliveryLog) {
		super(id,userName,password,fullName,email,contactNumber);
		this.zoneAssigned = zoneAssigned;
		this.dailyDeliveryLog = dailyDeliveryLog;
	}



	public String getZoneAssigned() {
		return zoneAssigned;
	}

	public void setZoneAssigned(String zoneAssigned) {
		this.zoneAssigned = zoneAssigned;
	}



	public List<DailyDeliveryLog> getDailyDeliveryLog() {
		return dailyDeliveryLog;
	}



	public void setDailyDeliveryLog(List<DailyDeliveryLog> dailyDeliveryLog) {
		this.dailyDeliveryLog = dailyDeliveryLog;
	}


	
}