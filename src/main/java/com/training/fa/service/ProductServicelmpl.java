package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.training.fa.model.Product;
import com.training.fa.repository.ProductRepository;

@Service
public class ProductServicelmpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Override
	
	
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product getProduct(Integer id) {
		
		 Optional<Product> productOption = productRepository.findById(id);
		 if(productOption.isPresent()) {
			 return productOption.get();
		 }
			throw new RuntimeException("not find by id entity Product!!");
		
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);

	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Page<Product> getAllPageProduct(Pageable pageable) {
		return productRepository.findAll(PageRequest.of(0, 5));
	}
     
	

}
