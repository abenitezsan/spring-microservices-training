package com.mimacom.training.microservices.adbe.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name = "consumer")
public class ConsumerEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "id")
	private long  id;

	@Column(nullable = false, name = "firstname")
	private String firstName;

	@Column(nullable = false, name = "lastname")
	private String lastName;

	@Column(nullable = false, name = "nif")
	private String nif;

	@Column(nullable = false, name = "createdat")
	private Date createdAt;

	@Column(nullable = false, name = "totalspent")
	private long totalSpent;

	@Column(nullable = false, name = "vip")
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
