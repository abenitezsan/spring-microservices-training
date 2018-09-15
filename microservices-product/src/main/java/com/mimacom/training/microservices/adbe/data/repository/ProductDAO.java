package com.mimacom.training.microservices.adbe.data.repository;

import com.mimacom.training.microservices.adbe.data.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDAO extends CrudRepository<ProductEntity,Long> {
	

}

