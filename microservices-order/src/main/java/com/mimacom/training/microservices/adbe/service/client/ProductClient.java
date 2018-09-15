package com.mimacom.training.microservices.adbe.service.client;

import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import feign.Body;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("products-microservice")
public interface ProductClient {


    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    ProductDTO getProduct(@PathVariable("productId") String productId);


    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}/stock")
    @Headers("Content-Type: application/json")
    ProductDTO updateStock(@PathVariable("productId") String productId,ProductDTO product);



}