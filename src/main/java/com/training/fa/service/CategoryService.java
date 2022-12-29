package com.training.fa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.fa.model.Category;


public interface CategoryService {
	
	
	
	  //get ds Category
		List<Category> getAllCategory();
		
		//save 1 entity(khoa)
		Category saveCategory(Category category);
		
		//get 1 entity(khoa) thong qua id
		Category getCategory(Integer id);
		
		//delete 1 doi tuong thong qua id
		void deleteCategory(Integer id);
		
		//update 1 entity(khoa) 
		Category updateCategory(Category category);
		
		
		

}
