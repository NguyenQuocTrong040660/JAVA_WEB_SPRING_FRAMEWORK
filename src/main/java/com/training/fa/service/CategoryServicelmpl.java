package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.fa.model.Category;
import com.training.fa.repository.CategoryRepository;

@Service
public class CategoryServicelmpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategory(Integer id) {
		
		 Optional<Category> khoaOption = categoryRepository.findById(id);
		 if(khoaOption.isPresent()) {
			 return khoaOption.get();
		 }
			throw new RuntimeException("not find by id entity Category!!");
		
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Category updateCategory(Category category) {
		
		return categoryRepository.save(category);
	}

}
