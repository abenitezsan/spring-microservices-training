package com.mimacom.training.microservices.adbe.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.mimacom.training.microservices.adbe.service.ProductService;
import com.mimacom.training.microservices.adbe.interfaces.dto.ProductDTO;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/products")
public class Controller {


	@Autowired
	private ProductService productService;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public List<ProductDTO> findAll(){

		List<ProductDTO> products= new ArrayList();

		return   productService.getAll();
	}


	@RequestMapping(value="/{productId}", method= RequestMethod.GET)
	public ProductDTO getProduct(@PathVariable long productId){
		return   productService.getProduct(productId);
	}

	@RequestMapping(value="/{productId}", method= RequestMethod.DELETE)
	public void deleteProduct(@PathVariable long productId){
		productService.deleteProduct(productId);
	}

	@RequestMapping(value="/{productId}", method= RequestMethod.PUT)
	public ProductDTO updateProduct(@RequestBody ProductDTO productDTO,@PathVariable long productId ){
		productDTO.setId(productId);
		return productService.updateProduct(productDTO);
	}

	@RequestMapping(value="/{productId}/stock", method= RequestMethod.PUT)
	public ProductDTO reserveProducts(@RequestBody ProductDTO productDTO,@PathVariable long productId ){
		ProductDTO product=productService.getProduct(productId);
		product.setStock(product.getStock()-productDTO.getStock());
		return productService.updateProduct(product);
	}

	@RequestMapping(value="/", method= RequestMethod.POST)
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO ){
		return productService.create(productDTO);
	}


}
