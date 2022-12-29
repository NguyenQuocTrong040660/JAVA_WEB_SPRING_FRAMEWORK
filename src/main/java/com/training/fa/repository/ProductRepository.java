package com.training.fa.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.fa.model.Category;
import com.training.fa.model.Product;
import com.training.fa.model.ProductImage;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT e FROM Product e")
	Page<Product> findProducts(Pageable pageable);
	
	List<Product> findByCategory(Category category);
	
	
	Page<Product>findByCategoryId(Integer id,Pageable pageable);
	
	Page<Product> findByNameContaining(String name,Pageable pageable);
	
}
