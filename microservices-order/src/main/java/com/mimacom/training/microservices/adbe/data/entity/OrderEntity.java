package com.mimacom.training.microservices.adbe.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name = "customerorder")
public class OrderEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false, name = "id")
	private long  id;

	@Column(nullable = false, name = "customerid")
	private long customerId;

	@Column(nullable = false, name = "createdat")
	private Date createdAt;

	@Column(nullable = false, name = "balance")
	private double balance;

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
}
