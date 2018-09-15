package com.mimacom.training.microservices.adbe.interfaces.dto;

import java.util.Date;

public class ProductDTO {

	private long  id;
	private String title;
	private String description;
	private Integer stock;
	private Date createdAt;
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

	public Integer getStock() {

		return stock;
	}

	public void setStock(Integer stock) {

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
