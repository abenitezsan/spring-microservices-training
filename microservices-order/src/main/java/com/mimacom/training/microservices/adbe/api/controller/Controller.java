package com.mimacom.training.microservices.adbe.api.controller;

import java.util.List;

import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import com.mimacom.training.microservices.adbe.service.OrderService;
import com.mimacom.training.microservices.adbe.interfaces.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@RestController("/orders")
public class Controller {


	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public List<OrderDTO> findAll(){


		return   orderService.getAll();
	}

	@RequestMapping(value="/{orderId}", method= RequestMethod.GET)
	public OrderDTO getProduct(@PathVariable long orderId){
		return   orderService.getOrder(orderId);
	}

	@RequestMapping(value="/", method= RequestMethod.POST)
	public OrderDTO addOrder(@RequestBody OrderDTO orderDTO){


		return   orderService.create(orderDTO);
	}



}
