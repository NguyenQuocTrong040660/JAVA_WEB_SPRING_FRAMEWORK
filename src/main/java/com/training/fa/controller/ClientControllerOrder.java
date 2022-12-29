package com.training.fa.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.fa.model.Order;
import com.training.fa.model.OrderDetail;
import com.training.fa.model.Shipping;
import com.training.fa.model.User;
import com.training.fa.repository.OrderRepository;
import com.training.fa.service.OrderDetailService;
import com.training.fa.service.OrderService;
import com.training.fa.service.UserService;
import com.training.fa.uitils.FileUtill;
import com.training.fa.userprinciple.UserDetaillmpl;
import com.training.fa.uitils.DateUtils;


@Controller
@RequestMapping("/home")
public class ClientControllerOrder {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping("/order")
	public String order(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page,
			
			
			HttpServletRequest request, Authentication authentication) {

		try {
			UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();

			if (userDetail != null) {
				Long id = userDetail.getId();
				User user = userService.getUser(id);
				Sort sort = Sort.by(Direction.DESC, field.orElse("createTime"));
				int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
				Pageable pageable = PageRequest.of(p, 5, sort);
				model.addAttribute("field", field.orElse("createTime"));

				
				Page<Order> orders  = orderService.findByUser(user, pageable);
	        	model.addAttribute("orders", orders);

				model.addAttribute("body", getBody("list-order"));
				return "index";
			}

			
			return "redirect:/home";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/login";
		}

	}

	@GetMapping("/filter-order")
	public String filterOrder(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("orderStatus") Integer orderStatus,
			
			HttpServletRequest request, Authentication authentication) {

		try {
			UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();
			if (userDetail != null ) {
				Long id = userDetail.getId();
				User user = userService.getUser(id);
				Sort sort = Sort.by(Direction.DESC, field.orElse("createTime"));
				int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
				Pageable pageable = PageRequest.of(p, 5, sort);
				model.addAttribute("field", field.orElse("createTime"));
				Page<Order> orders = orderService.findByUserAndOrderStatus(user, orderStatus, pageable);
				
				model.addAttribute("orderStatus", orderStatus);
				
				
				model.addAttribute("orders", orders);
				model.addAttribute("body", getBody("list-order"));
				return "index";

			}

			
			return "redirect:/home";
		} catch (Exception e) {
			
			return "redirect:/login";
		}

	}
	

	@GetMapping("/detail-order")
	public String detailOrder(Model model, @RequestParam("id") Integer id) {

		try {
		List<OrderDetail> orderDetails = orderDetailService.findByOrderID(id);
		Integer subtotal = 0;
		Integer discount = 0;
		for (OrderDetail item : orderDetails) {
			subtotal += item.getPrice() * item.getQuantity();
			discount = item.getOrder().getCoupon().getUseValue();

		}

		Integer total = (int) (subtotal - (subtotal * discount * 0.01));

		if (orderDetails.size() > 0) {

			model.addAttribute("subtotal", subtotal);
			model.addAttribute("total", total);
			model.addAttribute("orderDetails", orderDetails);
			model.addAttribute("body", getBody("detail-order"));
			return "index";
		}
		
		return "redirect:/home/order";
		
		}catch (Exception e) {
			return "redirect:/home/order";
		}

	}
	
	@GetMapping("/delete-order")
	public String deleteOrder(Model model,@RequestParam("id")Integer id) {
		try {
			orderService.deleteOrder(id);
			return "redirect:/home/order";
			
		}catch(Exception e) {
			return "redirect:/home/order";
		
		}
	}
	
	
	
	

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
