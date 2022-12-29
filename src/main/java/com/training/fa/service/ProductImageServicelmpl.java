package com.training.fa.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.training.fa.model.Product;
import com.training.fa.model.ProductImage;
import com.training.fa.repository.ProductImageRepository;

@Service
public class ProductImageServicelmpl implements ProductImageService {

	 
	 
	 @Autowired 
	 private ProductImageRepository  productImageRepository;
	
	@Override
	public byte[] read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductImage save(ProductImage productImage) {
		
		return productImageRepository.save(productImage);
	}

	@Override
	public List<ProductImage> getImages() {
		
		return productImageRepository.findAll();
	}

	
	@Override
	public List<ProductImage> getImageByProduct(Integer productid) {
		
		return productImageRepository.findByProductId(productid); 
	}

	@Override
	public void deleteImagesDetailByProductID(Integer id) {
		productImageRepository.deleteById(id);
		
	}
	


	

}
