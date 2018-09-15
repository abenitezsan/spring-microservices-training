package com.mimacom.training.microservices.adbe.data.repository;

import com.mimacom.training.microservices.adbe.data.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDAO extends CrudRepository<OrderEntity,Long> {
	

}

