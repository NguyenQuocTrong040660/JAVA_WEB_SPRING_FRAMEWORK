package com.training.fa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.fa.DTO.request.EmailDetails;
import com.training.fa.model.Category;
import com.training.fa.model.Order;
import com.training.fa.model.OrderDetail;
import com.training.fa.model.Product;
import com.training.fa.model.Revenues;
import com.training.fa.repository.OrderRepository;
import com.training.fa.repository.RevenuesRepository;
import com.training.fa.service.EmailService;
import com.training.fa.service.OrderDetailService;
import com.training.fa.service.OrderService;
import com.training.fa.service.RevenuesService;

@Controller
@RequestMapping("/admin")
public class AdminControllerOrder {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private RevenuesService revenuesService;

	@GetMapping("/orders")
	public String products(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page, HttpServletRequest request) {

		Sort sort = Sort.by(Direction.DESC, field.orElse("orderStatus"));
		int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
		Pageable pageable = PageRequest.of(p, 5, sort);
		model.addAttribute("field", field.orElse("orderStatus"));

		Page<Order> orders = orderRepository.findOrders(pageable);
		model.addAttribute("orders", orders);
		
		//Fillter Order Status
		
		//
		

		// annouce new Order
		Integer new_order = 0;
		List<Order> news_orders = orderRepository.findOrderByStatus0();
		new_order = news_orders.size();
		request.getSession().setAttribute("news_orders", news_orders);
		request.getSession().setAttribute("new_order", new_order);
		// annouce new Order

		model.addAttribute("body", getBody("./order/list-order"));
		return "admin/index";
	}
	
	@GetMapping("/filed-orders")
	public String orderByStatus(Model model,@RequestParam("orderStatus") Integer orderStatus,@RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page, HttpServletRequest request) {

		Sort sort = Sort.by(Direction.DESC, field.orElse("createTime"));
		int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
		Pageable pageable = PageRequest.of(p, 5, sort);
		model.addAttribute("field", field.orElse("createTime"));

		Page<Order> orders = orderService.findByorderStatus(orderStatus, pageable);
	//	System.out.println(orders.toString());
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("orders", orders);
		
		// annouce new Order
		Integer new_order = 0;
		List<Order> news_orders = orderRepository.findOrderByStatus0();
		new_order = news_orders.size();
		request.getSession().setAttribute("news_orders", news_orders);
		request.getSession().setAttribute("new_order", new_order);
		// annouce new Order

		model.addAttribute("body", getBody("./order/list-order"));
		return "admin/index";
	}
	
	
	
	

	@GetMapping("/detail-order")
	public String detailOrder(Model model, @RequestParam("id") Integer id) {

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
			model.addAttribute("body", getBody("./order/detail-order"));
			return "admin/index";
		} else {
			return "redirect:/admin/orders";
		}

	}

	// update status order when new order
	@GetMapping("/status-order")
	public String updateStatus(Model model,HttpServletRequest request,@RequestParam("id") Integer id) throws ParseException {
		Integer orderStatus = 1;
		int number = orderRepository.setStatusForOrders(orderStatus, id);
		if(number > 0) {
			
			//senMail
			Order order = orderService.getOrder(id);
			LocalDateTime dtm = LocalDateTime.now();  
			EmailDetails emailDetails = new EmailDetails();
			List<OrderDetail> orderDetails = orderDetailService.findByOrderID(id);
			Integer subtotal = 0;
			Integer discount =0;
			for(OrderDetail item:orderDetails) {
				subtotal += item.getPrice()*item.getQuantity();
				discount = item.getOrder().getCoupon().getUseValue();
				
			}
			
			Integer total = (int) (subtotal - (subtotal*discount*0.01));
			emailDetails.setRecipient(order.getUser().getEmail());
			
			String msgBody = "<p><b> Hey </b>"+ order.getUser().getUsername()+"</p>";
			msgBody += "<p><b>Order #CB"+ order.getId() +"</b> has been placed successfully and we are processing"+"</p>";
			msgBody += "<p><i> Cash on delivery"+"</i></p>";
			msgBody += "<p><b> [Order #CB"+ order.getId()+"]</b>"+" "+"("+dtm.toLocalDate()+")"+"</p>";
			msgBody += "<div class=\"row\">";
			msgBody += "<div class=\"card shadow mb-4\">";
			msgBody += "<div class=\"card-body\">";
			msgBody += "<div class=\"table-responsive\">";
			msgBody += "<table class=\"table\" id=\"dataTable\" cellspacing=\"1\">";
			msgBody += "<thead style=\"thead-dark\">";
			msgBody += "<tr>";
			msgBody +=    "<th>Items Summary </th>";
			msgBody +=   "</tr>";
			msgBody += "</thead>";
			
			msgBody += "<tbody>";
			
			for(OrderDetail item:orderDetails) {
			msgBody += "<tr>";
			msgBody += "<td> # "+item.getProduct().getName()+"   x"+item.getQuantity()+" $"+item.getPrice() +"</td>";
			
			msgBody += "</tr>";
			}
			msgBody += "</tbody>";
			msgBody += "</table>";
			msgBody += "</div>";
			msgBody += "</div>";
			msgBody += "</div>";
			msgBody += "</div>";
			msgBody +=  "<p><b> Subtotal $"+subtotal+"</b>"+"</p>";
			msgBody +=  "<p><b> Discount "+discount+" %</b>"+"</p>";
			msgBody +=  "<p><b> Total    $"+total+"</b>"+"</p>";
			
			emailDetails.setMsgBody(msgBody);
			emailDetails.setSubject("Orders at BIKE CYCLE STORE have been placed!");
			
			emailService.sendSimpleMail(emailDetails);
			
			  
			//End SentMail
			//
			   Integer new_order =0;
			   List<Order> news_orders = orderRepository.findOrderByStatus0();
			   new_order = news_orders.size();
			   request.getSession().setAttribute("news_orders", news_orders);
			   request.getSession().setAttribute("new_order", new_order);
			   
			//
			   
			//Begin Revenues
			   
			   String dateStr = order.getCreateTime().toString();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
			    String dateStrs =formatter1.format(formatter.parse(dateStr));
					
				
				Revenues revenues = revenuesService.findByOrderDate(dateStrs);
			  
			   if(revenues==null) {
				   Revenues revenuesSave = new Revenues();
				   revenuesSave.setOrderDate(dateStrs);
				   revenuesSave.setSales(total);
				   revenuesSave.setProfit((int) (total*50*0.01));
				   revenuesSave.setTotalOrder(1);
				   revenuesService.saveRevenues(revenuesSave);
				   
			   }else {
				   Revenues revenuesSave = new Revenues();
				   revenuesSave.setId(revenues.getId());
				   revenuesSave.setOrderDate(revenues.getOrderDate());
				   revenuesSave.setSales(revenues.getSales()+total);
				   revenuesSave.setProfit(revenues.getProfit()+(int) (total*50*0.01));
				   revenuesSave.setTotalOrder(revenues.getTotalOrder()+1);
				   revenuesService.updateRevenues(revenuesSave);
				     
			   }
			   
			 return "redirect:/admin/orders";
		}
		else{
			return "redirect:/admin/";
		}
	}

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
