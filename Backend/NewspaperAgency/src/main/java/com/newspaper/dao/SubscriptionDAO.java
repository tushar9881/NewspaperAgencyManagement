package com.newspaper.dao;

import java.time.LocalDate;

public class SubscriptionDAO {

	private Integer Id;
	private String paperName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double price;
	private Integer quantity;
	private Integer total;
	
	public SubscriptionDAO() {
		// TODO Auto-generated constructor stub
	}

	public SubscriptionDAO(Integer id, String paperName, LocalDate startDate, LocalDate endDate, Double price,
			Integer quantity, Integer total) {
		super();
		Id = id;
		this.paperName = paperName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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
	
	
}
