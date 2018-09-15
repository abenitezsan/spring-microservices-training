package com.mimacom.training.microservices.adbe.data.repository;

import com.mimacom.training.microservices.adbe.data.entity.OrderEntity;
import com.mimacom.training.microservices.adbe.data.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDAO extends CrudRepository<OrderProductEntity,Long> {

    @Query("select productId from orderproducts o where o.orderId=:orderId")
    List<Long> getProductsIds(@Param("orderId")long orderId);

}

