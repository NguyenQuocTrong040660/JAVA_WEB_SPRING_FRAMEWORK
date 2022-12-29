package com.training.fa.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.training.fa.model.Product;
import com.training.fa.model.ProductImage;
import com.training.fa.service.ProductImageService;
import com.training.fa.service.ProductService;
import com.training.fa.uitils.FileUtill;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductImageController {
 //   private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	

	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileUtill fileUtill;
	
	@PostMapping("/save-file")
	public ProductImage saveProductImage(
			@RequestParam MultipartFile image,
			@RequestParam("product_id") Integer product_id) throws IOException {
		   
		    String path = fileUtill.saveFile(CURRENT_FOLDER, image);
		   
	        ProductImage  productImage = new ProductImage();
	        
	        
	        productImage.setProduct(productService.getProduct(product_id));
	        productImage.setName(image.getOriginalFilename());
	       
	        productImage.setPath(path);
	        
	        return  productImageService.save(productImage);
	
		
	}
	
	@GetMapping("/list-productImages")
	public List<ProductImage> listproductImages(){
		return productImageService.getImages();
	}
	
	
	
    @Autowired
	private ServletContext servletContext;

	

	

	private String saveImage(MultipartFile multipartFile) {
		try {
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/resources/http://learningprogramming.net/wp-content/uploads/java/spring_mvc/" + multipartFile.getOriginalFilename()));
			Files.write(path, bytes);
			return multipartFile.getOriginalFilename();
		} catch (IOException e) {
			return null;
		}
	}

	
	
	
}
