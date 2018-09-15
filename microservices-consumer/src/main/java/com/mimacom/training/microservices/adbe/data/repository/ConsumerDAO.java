package com.mimacom.training.microservices.adbe.data.repository;

import com.mimacom.training.microservices.adbe.data.entity.ConsumerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsumerDAO extends CrudRepository<ConsumerEntity,Long> {
	

}

