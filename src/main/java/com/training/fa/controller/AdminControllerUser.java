package com.training.fa.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.fa.model.User;
import com.training.fa.service.RoleService;
import com.training.fa.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminControllerUser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	public String getAllUser(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page, HttpServletRequest request) {
		
		
		Pageable pageable = PageRequest.of(page.orElse(0), 5);
		Page<User> user = userService.findAll(pageable);
		
		model.addAttribute("users", user);
		model.addAttribute("roles", roleService.findAll());
		
		
		model.addAttribute("body", getBody("user/list-user"));
		return "admin/index";
	}
	
	@GetMapping("/user/delete")
	public String deleteUser(Model model, @RequestParam("id") long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = userService.getUser(id);
				
		if(user.getOrders().size()==0) {
			userService.deleteById(id);
			redirectAttributes.addFlashAttribute("message","User is deleted!");
			
		}else {
			redirectAttributes.addFlashAttribute("message","user has an order!");
		}
				
		return "redirect:/admin/users";
	}
	
	public static String getBody(String body) {
		return body + ".jsp";
	}
	

}
