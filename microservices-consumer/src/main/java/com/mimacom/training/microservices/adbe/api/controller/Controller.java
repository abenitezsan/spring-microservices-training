package com.mimacom.training.microservices.adbe.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.mimacom.training.microservices.adbe.service.ConsumerService;
import com.mimacom.training.microservices.adbe.interfaces.dto.ConsumerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/consumers")
public class Controller {


	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public List<ConsumerDTO> findAll(){


		return   consumerService.getAll();
	}


	@RequestMapping(value="/{consumerId}", method= RequestMethod.GET)
	public ConsumerDTO getProduct(@PathVariable long consumerId){
		return   consumerService.getConsumer(consumerId);
	}

	@RequestMapping(value="/{consumerId}", method= RequestMethod.DELETE)
	public void deleteConsumer(@PathVariable long consumerId){
		consumerService.deleteConsumer(consumerId);
	}

	@RequestMapping(value="/{consumerId}", method= RequestMethod.PUT)
	public ConsumerDTO updateConsumer(@RequestBody ConsumerDTO consumerDTO,@PathVariable long consumerId ){
		consumerDTO.setId(consumerId);
		return consumerService.updateConsumer(consumerDTO);
	}

	@RequestMapping(value="/", method= RequestMethod.POST)
	public ConsumerDTO createConsumer(@RequestBody ConsumerDTO consumerDTO ){
		return consumerService.create(consumerDTO);
	}


}
