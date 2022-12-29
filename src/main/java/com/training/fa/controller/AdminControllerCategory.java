package com.training.fa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.fa.model.Category;
import com.training.fa.service.CategoryService;


@RequestMapping("/admin")
@Controller
public class AdminControllerCategory {
	
   @Autowired
   private CategoryService categoryService;

	@GetMapping("/category")
	public String categories(Model model) {
		
		model.addAttribute("body", getBody("./category/list-category"));
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		return "admin/index";
	}
	
	
	
	@PostMapping("/category")
	public String saveCategory(Model model,@RequestParam("name") String name) {
		
		Category category = new Category();
		category.setName(name);
		Category categorysave = categoryService.saveCategory(category);
       if(categorysave!=null) {
    	   
    	   model.addAttribute("body", getBody("./category/list-category"));
    	  
       }
		
       model.addAttribute("message", "Submit category failed");
       return "redirect:/admin/category";
		
		
	}
	
	@GetMapping("/delete-category")
	public String deleteCategory(Model model,@RequestParam("id") Integer id) {
		
		
		 categoryService.deleteCategory(id);
       
    	 model.addAttribute("body", getBody("./category/list-category"));
    	  
      
       return "redirect:/admin/category";
		
		
	}
	
	@GetMapping("/update-category")
	public String updateCategory(Model model,@RequestParam("id") Integer id) {
		
		
		Category category =  categoryService.getCategory(id);
		model.addAttribute("category", category);
       
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		
    	model.addAttribute("body", getBody("./category/update-category"));
    	  
      
       return "admin/index";
		
		
	}
	
	@PostMapping("/update-category")
	public String updateSaveCategory(Model model,@RequestParam("id") Integer id,@RequestParam("name") String name) {
		
		
		Category category =  categoryService.getCategory(id);
		category.setName(name);
		
		categoryService.updateCategory(category);
       
    	model.addAttribute("body", getBody("./category/list-category"));
    	  
       return "redirect:/admin/category";
		
		
	}
	
	
	public static String getBody(String body) {
		return body+".jsp";
	}

}
