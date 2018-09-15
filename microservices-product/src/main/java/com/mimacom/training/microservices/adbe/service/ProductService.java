package com.mimacom.training.microservices.adbe.service;

import com.mimacom.training.microservices.adbe.data.entity.ProductEntity;
import com.mimacom.training.microservices.adbe.data.repository.ProductDAO;
import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author adbe
 */
@Service
public class ProductService {

	@Autowired
	ProductDAO productDAO;



	public List<ProductDTO> getAll(){
		return StreamSupport.stream(productDAO.findAll().spliterator(),false).map(productEntity -> 	getProductDTO(productEntity)).collect(Collectors.toList());
	}

	public ProductDTO create(ProductDTO productDTO){

		return getProductDTO(productDAO.save(getProductEntity(productDTO)));
	}

	public ProductDTO updateProduct(ProductDTO productDTO){

		ProductEntity productEntity= productDAO.findById(productDTO.getId()).get();
		final BeanWrapper wrappedSource = new BeanWrapperImpl(productDTO);
		String[] ignoreProperties= Stream.of(wrappedSource.getPropertyDescriptors())
					 .map(FeatureDescriptor::getName)
					 .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
					 .toArray(String[]::new);

		BeanUtils.copyProperties(productDTO,productEntity,ignoreProperties);
		return getProductDTO(productDAO.save(productEntity));
	}

	public ProductDTO getProduct(long productId){
		return getProductDTO(productDAO.findById(productId).get());
	}

	public void deleteProduct(long productId){
		productDAO.deleteById(productId);
	}

	private ProductDTO getProductDTO(ProductEntity productEntity){
		ProductDTO productDTO=new ProductDTO();
		BeanUtils.copyProperties(productEntity,productDTO);
		return productDTO;
	}

	private ProductEntity getProductEntity(ProductDTO productDTO){
		ProductEntity productEntity=new ProductEntity();
		BeanUtils.copyProperties(productDTO,productEntity);
		return productEntity;
	}
}
