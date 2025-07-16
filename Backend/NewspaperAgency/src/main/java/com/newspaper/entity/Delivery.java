package com.newspaper.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Integer id;

    private String userName;
    private String address;

    private boolean delivered;
    private LocalDateTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "daily_log_id")
    @JsonBackReference("dreference")
    private DailyDeliveryLog dailyDeliveryLog;

    @ManyToOne
    @JsonBackReference("creference")
    private Customer customer; // if you want full link
    
    public Delivery() {
		// TODO Auto-generated constructor stub
	}

	public Delivery(Integer id, String userName, String address, boolean delivered, LocalDateTime deliveryTime,
			DailyDeliveryLog dailyDeliveryLog, Customer customer) {
		super();
		this.id = id;
		this.userName = userName;
		this.address = address;
		this.delivered = delivered;
		this.deliveryTime = deliveryTime;
		this.dailyDeliveryLog = dailyDeliveryLog;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public DailyDeliveryLog getDailyDeliveryLog() {
		return dailyDeliveryLog;
	}

	public void setDailyDeliveryLog(DailyDeliveryLog dailyDeliveryLog) {
		this.dailyDeliveryLog = dailyDeliveryLog;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", userName=" + userName + ", address=" + address + ", delivered=" + delivered
				+ ", deliveryTime=" + deliveryTime + ", dailyDeliveryLog=" + dailyDeliveryLog + ", customer=" + customer
				+ "]";
	}
    
    
}
