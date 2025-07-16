package com.newspaper.dao;

import java.time.LocalDateTime;

import com.newspaper.entity.Customer;
import com.newspaper.entity.DailyDeliveryLog;

public class DeliveryDAO {
	
	private Integer id;
    private String userName;
    private String address;
    private boolean delivered;
    private LocalDateTime deliveryTime;
    private Integer dailyDeliveryLogid;
    private Integer customerId;
    
    public DeliveryDAO() {
		// TODO Auto-generated constructor stub
	}

	public DeliveryDAO(Integer id, String userName, String address, boolean delivered, LocalDateTime deliveryTime,
			Integer dailyDeliveryLogid, Integer customerId) {
		super();
		this.id = id;
		this.userName = userName;
		this.address = address;
		this.delivered = delivered;
		this.deliveryTime = deliveryTime;
		this.dailyDeliveryLogid = dailyDeliveryLogid;
		this.customerId = customerId;
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

	public Integer getDailyDeliveryLogid() {
		return dailyDeliveryLogid;
	}

	public void setDailyDeliveryLogid(Integer dailyDeliveryLogid) {
		this.dailyDeliveryLogid = dailyDeliveryLogid;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
    
    
}
