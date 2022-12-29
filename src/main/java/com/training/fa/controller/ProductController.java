package com.training.fa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.fa.model.Product;
import com.training.fa.service.ProductService;

import lombok.ToString;

@ToString
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService  ProductService;
	
	@PostMapping("/save-product")
	public Product saveProduct(@Valid @RequestBody Product Product ) {
	
		return ProductService.saveProduct(Product);

	}
		@GetMapping("/get-products")
		public List<Product> getProducts() {
		    
		
			
			return ProductService.getAllProduct();
			
		}
	
	
	

}
