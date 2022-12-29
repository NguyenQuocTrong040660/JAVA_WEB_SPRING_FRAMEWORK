package com.training.fa.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.training.fa.model.Product;

public interface ProductService {
	

	
	//get ds Product
		List<Product> getAllProduct();
		Page<Product> getAllPageProduct(Pageable pageable);
		//save 1 entity(khoa)
		Product saveProduct(Product product);
		
		//get 1 entity(khoa) thong qua id
		Product getProduct(Integer id);
		
		//delete 1 doi tuong thong qua id
		void deleteProduct(Integer id);
		
		//update 1 entity(khoa) 
		Product updateProduct(Product product);
		
	   
		
		

}
