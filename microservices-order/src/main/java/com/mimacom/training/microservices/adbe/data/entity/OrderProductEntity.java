package com.mimacom.training.microservices.adbe.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "orderproducts")
@IdClass(OrderProductKey.class)
public class OrderProductEntity {

    @Id
	@Column(nullable = false, name = "orderid")
	private long  orderId;

    @Id
	@Column(nullable = false, name = "productid")
	private long productId;

	@Column(nullable = false, name = "amount")
	private int amount;

	public long getOrderId() {

		return orderId;
	}

	public void setOrderId(long orderId) {

		this.orderId = orderId;
	}

	public long getProductId() {

		return productId;
	}

	public void setProductId(long productId) {

		this.productId = productId;
	}

	public int getAmount() {

		return amount;
	}

	public void setAmount(int amount) {

		this.amount = amount;
	}
}
