package com.training.fa.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.fa.model.Order;
import com.training.fa.model.Product;
import com.training.fa.model.Revenues;
import com.training.fa.model.User;
import com.training.fa.repository.OrderRepository;
import com.training.fa.repository.RevenuesRepository;
import com.training.fa.service.OrderService;
import com.training.fa.service.ProductService;
import com.training.fa.service.RevenuesService;
import com.training.fa.service.UserDetailServicelmpl;
import com.training.fa.service.UserService;
import com.training.fa.userprinciple.UserDetaillmpl;

@RequestMapping("/admin")

@Controller
public class AdminController {
	
	
	@Autowired
	private OrderService  orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RevenuesService revenuesService;
	
	@Autowired
	private RevenuesRepository revenuesRepository;
	
	
   @GetMapping("/403")
	   public String Error(Model model,Principal principal) {
	   if (principal != null) {
        String message = "Hi " + principal.getName() //
                + "<br> You do not have permission to access this page!";
        model.addAttribute("message", message);
	   }
		return "admin/index";
	  
   }
	
   @GetMapping("")
	public String adminHome(Model model,Principal principal,HttpServletRequest request,Authentication authentication)  {
	 
	 //lay username va passwork role id
	   if(principal!=null) {
		   String userDetail =  principal.getName();
		   model.addAttribute("message", userDetail);
	   }
	   
	   UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();
       request.getSession().setAttribute("userDetail", userDetail);
	   
	   //admin home
	   model.addAttribute("body", getBody("home/home"));
	 
		
	   //Create Announce New Order
	   Integer new_order =0;
	   List<Order> news_orders = orderRepository.findOrderByStatus0();
	   new_order = news_orders.size();
	   request.getSession().setAttribute("news_orders", news_orders);
	   request.getSession().setAttribute("new_order", new_order);
		
	   List<Order> orders = orderService.findOrderByStatus1();
	   model.addAttribute("count_order", orders.size());
	   
	   List<Product> products = productService.getAllProduct();
	   model.addAttribute("count_product", products.size());
	   
	   List<User> users = userService.getAllUser();
	   model.addAttribute("count_user", users.size());
	   
	   
		return "admin/index";
		
	}
	
	
	
	
	
	
	public static String getBody(String body) {
		return body+".jsp";
	}

	
	

}
