package com.mimacom.training.microservices.adbe.service.client;

import com.mimacom.training.microservices.adbe.interfaces.dto.ConsumerDTO;
import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("consumer-microservice")
public interface ConsumerClient {


    @RequestMapping(method = RequestMethod.GET, value = "/{consumerId}")
    ConsumerDTO getConsumer(@PathVariable("consumerId") String consumerId);


}