package com.training.fa.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.training.fa.model.Product;
import com.training.fa.model.ProductImage;

public interface ProductImageService {
	public byte[] read(String name);
	
	
	
	ProductImage save(ProductImage productImage);
	
	List<ProductImage> getImages();
	
	List<ProductImage> getImageByProduct(Integer productId);
	
	void deleteImagesDetailByProductID(Integer id);

}
