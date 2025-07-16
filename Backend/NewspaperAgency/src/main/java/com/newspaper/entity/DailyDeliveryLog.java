package com.newspaper.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; // ✅ CORRECT

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DailyDeliveryLog {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "delivery_executive_id")
    @JsonBackReference("deReference")
    private DeliveryExecutive deliveryExecutive;

    @OneToMany(mappedBy = "dailyDeliveryLog", cascade = CascadeType.ALL)
    @JsonManagedReference("dreference")
    private List<Delivery> deliveries = new ArrayList<>();

    
    public DailyDeliveryLog() {
		// TODO Auto-generated constructor stub
	}


	public DailyDeliveryLog(Integer id, LocalDate deliveryDate, DeliveryExecutive deliveryExecutive,
			List<Delivery> deliveries) {
		super();
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.deliveryExecutive = deliveryExecutive;
		this.deliveries = deliveries;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public DeliveryExecutive getDeliveryExecutive() {
		return deliveryExecutive;
	}


	public void setDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
		this.deliveryExecutive = deliveryExecutive;
	}


	public List<Delivery> getDeliveries() {
		return deliveries;
	}


	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}


	
    
    
	
}