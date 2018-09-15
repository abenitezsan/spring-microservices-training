package com.mimacom.training.microservices.adbe.service;

import com.mimacom.training.microservices.adbe.data.entity.OrderEntity;
import com.mimacom.training.microservices.adbe.data.entity.OrderProductEntity;
import com.mimacom.training.microservices.adbe.data.repository.OrderDAO;
import com.mimacom.training.microservices.adbe.data.repository.OrderProductDAO;
import com.mimacom.training.microservices.adbe.interfaces.dto.ConsumerDTO;
import com.mimacom.training.microservices.adbe.interfaces.dto.OrderDTO;
import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import com.mimacom.training.microservices.adbe.service.client.ConsumerClient;
import com.mimacom.training.microservices.adbe.service.client.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author adbe
 */
@Service
public class OrderService {

	@Autowired
    OrderDAO orderDAO;
	@Autowired
	OrderProductDAO orderProductDAO;

	@Autowired
	ProductClient productClient;

	@Autowired
	ConsumerClient consumerClient;


	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	public List<OrderDTO> getAll(){
		return StreamSupport.stream(orderDAO.findAll().spliterator(), false).map(orderEntity -> 	getOrderDTO(orderEntity)).collect(Collectors.toList());
	}


	private OrderDTO getOrderDTO(OrderEntity orderEntity){
		OrderDTO orderDTO =new OrderDTO();
		BeanUtils.copyProperties(orderEntity, orderDTO);
		List<ProductDTO> products=new LinkedList<>();
		for (Long productId:orderProductDAO.getProductsIds(orderEntity.getId())){
			products.add(productClient.getProduct(String.valueOf(productId)));
		}
		orderDTO.setProducts(products);

		return orderDTO;
	}

	@HystrixCommand(fallbackMethod = "reliableOrder",commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000")} )
	public OrderDTO getOrder(long orderId){

		return getOrderDTO(orderDAO.findById(orderId).get());
	}


	public OrderDTO create(OrderDTO orderDTO){

		ConsumerDTO consumerDTO=consumerClient.getConsumer(String.valueOf(orderDTO.getCustomerId()));

		if(consumerDTO.isVip()){
			orderDTO.setBalance(orderDTO.getBalance()*0.9);
		}

		OrderEntity entity=(orderDAO.save(getOrderEntity(orderDTO)));

		for(ProductDTO productDTO:orderDTO.getProducts()) {

			OrderProductEntity orderProductEntity=new OrderProductEntity();
			orderProductEntity.setAmount(1);
			orderProductEntity.setOrderId(entity.getId());
			orderProductEntity.setProductId(productDTO.getId());
			orderProductDAO.save(orderProductEntity);
			productClient.updateStock(String.valueOf(productDTO.getId()),productDTO);
		}



		kafkaTemplate.send("orders", String.valueOf(orderDTO.getCustomerId()), String.valueOf(orderDTO.getBalance()));




		return getOrderDTO(entity);
	}

	private OrderEntity getOrderEntity(OrderDTO orderDTO){
		OrderEntity orderEntity=new OrderEntity();
		BeanUtils.copyProperties(orderDTO,orderEntity);
		orderEntity.setCreatedAt(new Date());
		return orderEntity;
	}



	public OrderDTO reliableOrder(long orderId){
		OrderDTO orderDTO =new OrderDTO();
		BeanUtils.copyProperties(orderDAO.findById(orderId), orderDTO);
		List<ProductDTO> products=new LinkedList<>();

		orderDTO.setProducts(products);

		return orderDTO;
	}


}
