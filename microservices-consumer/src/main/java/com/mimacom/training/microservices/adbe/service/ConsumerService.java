package com.mimacom.training.microservices.adbe.service;

import com.mimacom.training.microservices.adbe.data.entity.ConsumerEntity;
import com.mimacom.training.microservices.adbe.data.repository.ConsumerDAO;
import com.mimacom.training.microservices.adbe.interfaces.dto.ConsumerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author adbe
 */
@Service
public class ConsumerService {

	@Autowired
	ConsumerDAO consumerDAO;



	public List<ConsumerDTO> getAll(){
		return StreamSupport.stream(consumerDAO.findAll().spliterator(),false).map(consumerEntity -> 	getConsumerDTO(consumerEntity)).collect(Collectors.toList());
	}

	public ConsumerDTO create(ConsumerDTO ConsumerDTO){
		ConsumerEntity consumerEntity=getConsumerEntity(ConsumerDTO);
		consumerEntity.setCreatedAt(new Date());
		return getConsumerDTO(consumerDAO.save(consumerEntity));
	}

	public ConsumerDTO updateConsumer(ConsumerDTO ConsumerDTO){

		ConsumerEntity consumerEntity= consumerDAO.findById(ConsumerDTO.getId()).get();
		final BeanWrapper wrappedSource = new BeanWrapperImpl(ConsumerDTO);
		String[] ignoreProperties= Stream.of(wrappedSource.getPropertyDescriptors())
					 .map(FeatureDescriptor::getName)
					 .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
					 .toArray(String[]::new);

		BeanUtils.copyProperties(ConsumerDTO,consumerEntity,ignoreProperties);
		return getConsumerDTO(consumerDAO.save(consumerEntity));
	}

	public ConsumerDTO getConsumer(long consumerID){
		return getConsumerDTO(consumerDAO.findById(consumerID).get());
	}


	@KafkaListener(topics = "orders", groupId = "foo")
	public void listen( @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key ,String message) {

		System.out.println("processing KAFKA order with  key "+key+" and  message "+message);
		ConsumerEntity consumerEntity=consumerDAO.findById(Long.parseLong(key)).get();
		consumerEntity.setTotalSpent((long)(consumerEntity.getTotalSpent()+Double.parseDouble(message)));
		if(consumerEntity.getTotalSpent()>10000){
			consumerEntity.setVip(true);
		}
		consumerDAO.save(consumerEntity);

	}
	public void deleteConsumer(long consumerID){
		consumerDAO.deleteById(consumerID);
	}

	private ConsumerDTO getConsumerDTO(ConsumerEntity consumerEntity){
		ConsumerDTO ConsumerDTO=new ConsumerDTO();
		BeanUtils.copyProperties(consumerEntity,ConsumerDTO);
		return ConsumerDTO;
	}

	private ConsumerEntity getConsumerEntity(ConsumerDTO ConsumerDTO){
		ConsumerEntity consumerEntity=new ConsumerEntity();
		BeanUtils.copyProperties(ConsumerDTO,consumerEntity);
		return consumerEntity;
	}
}
