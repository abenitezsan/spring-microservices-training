package com.mimacom.training.microservices.adbe.interfaces.dto;

import java.util.Date;

public class ConsumerDTO {

	private long  id;
	private String firstName;
	private String lastName;
	private String nif;
	private Date createdAt;
	private long totalSpent;
	private boolean vip;

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getNif() {

		return nif;
	}

	public void setNif(String nif) {

		this.nif = nif;
	}

	public Date getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {

		this.createdAt = createdAt;
	}

	public long getTotalSpent() {

		return totalSpent;
	}

	public void setTotalSpent(long totalSpent) {

		this.totalSpent = totalSpent;
	}

	public boolean isVip() {

		return vip;
	}

	public void setVip(boolean vip) {

		this.vip = vip;
	}
}
