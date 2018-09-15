package com.mimacom.training.microservices.adbe.interfaces.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {


	private long  id;
	private long customerId;
	private Date createdAt;
	private double balance;
	private List<ProductDTO> products;

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(long customerId) {

		this.customerId = customerId;
	}

	public Date getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {

		this.createdAt = createdAt;
	}

	public double getBalance() {

		return balance;
	}

	public void setBalance(double balance) {

		this.balance = balance;
	}

	public List<ProductDTO> getProducts() {

		return products;
	}

	public void setProducts(List<ProductDTO> products) {

		this.products = products;
	}
}
