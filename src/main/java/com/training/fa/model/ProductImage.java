package com.training.fa.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@ToString
@Entity
@Table
public class ProductImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String path;
	
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product product;

	public ProductImage() {
		super();
	}

	public ProductImage( String name, String path) {
		super();
		
		this.name = name;
		
		this.path = path;
	}
	
	

	public ProductImage( String name, String path, Product product) {
		super();
		
		this.name = name;
		this.path = path;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	

	
	
	

	
	
	

}
