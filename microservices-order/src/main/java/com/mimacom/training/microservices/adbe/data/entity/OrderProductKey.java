package com.mimacom.training.microservices.adbe.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

public class OrderProductKey implements Serializable {

	private long orderId;

	private long productId;
}
