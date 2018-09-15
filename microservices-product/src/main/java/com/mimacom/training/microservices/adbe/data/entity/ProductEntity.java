package com.mimacom.training.microservices.adbe.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name = "product")
public class ProductEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "id")
	private long  id;
	

	@Column(nullable = false, name = "title")
	private String title;

	@Column(nullable = false, name = "description")
	private String description;

	@Column(nullable = false, name = "stock")
	private int stock;


	@Column(nullable = false, name = "createdat")
	private Date createdAt;

	@Column(nullable = false, name = "modifiedtat")
	private Date modifiedtAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedtAt() {
		return modifiedtAt;
	}

	public void setModifiedtAt(Date modifiedtAt) {
		this.modifiedtAt = modifiedtAt;
	}
}
