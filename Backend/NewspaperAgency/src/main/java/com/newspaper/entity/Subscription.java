package com.newspaper.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriptions")

public class Subscription {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String paperName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double price;
	private Integer quantity;
	private Integer total;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference("creference")
	private Customer customer;

	public Subscription() {
		// TODO Auto-generated constructor stub
	}

	public Subscription(Integer id, String paperName, LocalDate startDate, LocalDate endDate, Double price,
			Integer quantity, Integer total, Customer customer) {
		this.id = id;
		this.paperName = paperName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}
	public Customer getCustomer() {
	    return customer;
	}


	@Override
	public String toString() {
		return "Subscriptions [id=" + id + ", paperName=" + paperName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", price=" + price + ", quantity=" + quantity + ", total=" + total + "]";
	}

}
