package com.training.fa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.fa.DTO.request.Cart;
import com.training.fa.DTO.response.JsonCode;
import com.training.fa.model.Coupon;
import com.training.fa.model.Order;
import com.training.fa.model.OrderDetail;
import com.training.fa.model.Product;
import com.training.fa.model.Shipping;
import com.training.fa.model.User;
import com.training.fa.repository.ProductRepository;
import com.training.fa.service.CategoryService;
import com.training.fa.service.CouponService;
import com.training.fa.service.OrderDetailService;
import com.training.fa.service.OrderService;
import com.training.fa.service.ProductService;
import com.training.fa.service.ShippingService;
import com.training.fa.service.UserService;
import com.training.fa.userprinciple.UserDetaillmpl;

@Controller
@RequestMapping("/home")
public class ClientControllerCheckout {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private ShippingService shippingService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@GetMapping("/checkout")
	public String checkOut(Model model, HttpServletRequest request, Principal principal ) {

		//Purchase
		if (principal != null && request.getSession().getAttribute("cartPurchuse")!=null ) {
			
			System.out.println("cartPurchuse");
			List<Coupon> coupons = couponService.getAllCoupon();
			model.addAttribute("coupons", coupons);

			model.addAttribute("body", getBody("checkout"));
			return "index";
		} //End Purchase
		if (principal != null && request.getSession().getAttribute("cartProduct") != null 
				&& request.getSession().getAttribute("cartPurchuse")==null ) {
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");

			List<Coupon> coupons = couponService.getAllCoupon();
			model.addAttribute("coupons", coupons);

			// ton tai mot coupon cu the => co % giam gia

			model.addAttribute("body", getBody("checkout"));
			return "index";
		} else if (request.getSession().getAttribute("cartProduct") != null) {

			return "redirect:/login";
		} else if(request.getSession().getAttribute("cartPurchuse") != null ) {
			return "redirect:/login";
		}else {
			return "redirect:/home";
		}
	}
	
	@GetMapping("/purechuse")
	public String pureChase(Model model, HttpServletRequest request, Principal principal,@RequestParam("id") Integer id) {
        
		try {
		
			request.getSession().removeAttribute("cartPurchuse");
			
			Product product = productService.getProduct(id);
            Cart dtoCart = new Cart();
            dtoCart.setId(product.getId());
            dtoCart.setName(product.getName());
            dtoCart.setPrice(product.getPrice());
            dtoCart.setQuantity(1);
            dtoCart.setTotalPrice(product.getPrice()*dtoCart.getQuantity());
            dtoCart.setContent(product.getContent());
            dtoCart.setImagePath(product.getImagePath());
            
            
			request.getSession().setAttribute("cartPurchuse", dtoCart);
		    System.out.println("cart purchase ton tai");
			if (principal == null ) {
			System.out.println("0002");
		    return "redirect:/login";
			}
			
			
			return "redirect:/home/checkout";
			
	}catch(Exception e) {
		System.out.println(e);
		System.out.println("0002");
		return "redirect:/home";
	}
		
		
	}


	//When place order success
	@PostMapping("/checkout")
	public String saveCheckout(Model model, HttpServletRequest request, Authentication authentication,
			@RequestParam("shippingName") String shippingName, @RequestParam("shippingPhone") String shippingPhone,
			@RequestParam("shippingAddress") String shippingAddress, @RequestParam("shippingNote") String shippingNote

	) {
		try {
		Shipping shippping = new Shipping(shippingName, shippingPhone, shippingAddress, shippingNote);

		Shipping shipppingSave = shippingService.saveShipping(shippping);
		UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();
		Long id = userDetail.getId();
		User user = new User();
		user = userService.getUser(id);
		
		
		Coupon counpon = (Coupon) request.getSession().getAttribute("counpon");
		if (shipppingSave != null && user!=null && counpon!=null && request.getSession().getAttribute("cartPurchuse")==null) {
			
			Order order = new Order(0, user, shipppingSave, counpon);
			Order orderSave = orderService.saveOrder(order);
            
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");
			
			for (Cart item : carts) {
				OrderDetail oderDetail = new OrderDetail();
				Product product = new Product();

				product = productService.getProduct(item.getId());

				oderDetail.setPrice(item.getPrice());
				oderDetail.setQuantity(item.getQuantity());
				oderDetail.setOrder(orderSave);
				oderDetail.setProduct(product);

				orderDetailService.saveOrderDetail(oderDetail);
				
			}
			//request.getSession().invalidate();
			request.getSession().removeAttribute("total_order");
			request.getSession().removeAttribute("counpon");
			request.getSession().removeAttribute("sum");
			request.getSession().removeAttribute("cartProduct");
			request.getSession().removeAttribute("countCart");
			
			
			
			return "redirect:/home/order";
			

		}if (shipppingSave != null && user!=null && counpon==null && request.getSession().getAttribute("cartPurchuse")==null) {
			
			Coupon counponDefault = couponService.findByuseValue(0);
			Order order = new Order(0, user, shipppingSave, counponDefault);
			Order orderSave = orderService.saveOrder(order);
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");
			
			for (Cart item : carts) {
				OrderDetail oderDetail = new OrderDetail();
				Product product = new Product();
				product = productService.getProduct(item.getId());
				oderDetail.setPrice(item.getPrice());
				oderDetail.setQuantity(item.getQuantity());
				oderDetail.setOrder(orderSave);
				oderDetail.setProduct(product);
				orderDetailService.saveOrderDetail(oderDetail);
			}
			//request.getSession().invalidate();
			request.getSession().removeAttribute("total_order");
			request.getSession().removeAttribute("counpon");
			request.getSession().removeAttribute("sum");
			request.getSession().removeAttribute("cartProduct");
			request.getSession().removeAttribute("countCart");
			return "redirect:/home/order";
		}
		
		if (shipppingSave != null && user!=null && request.getSession().getAttribute("cartPurchuse")!=null ) {
			
			Coupon counponDefault = couponService.findByuseValue(0);
			Order order = new Order(0, user, shipppingSave, counponDefault);
			Order orderSave = orderService.saveOrder(order);
			
			Cart carts =  (Cart) request.getSession().getAttribute("cartPurchuse");
			
			
				OrderDetail oderDetail = new OrderDetail();
				Product product = new Product();
				product = productService.getProduct(carts.getId());
				
				oderDetail.setPrice(carts.getPrice());
				oderDetail.setQuantity(carts.getQuantity());
				oderDetail.setOrder(orderSave);
				oderDetail.setProduct(product);
				orderDetailService.saveOrderDetail(oderDetail);
			
			//request.getSession().invalidate();
			request.getSession().removeAttribute("total_order");
			request.getSession().removeAttribute("counpon");
			request.getSession().removeAttribute("sum");
			request.getSession().removeAttribute("cartProduct");
			request.getSession().removeAttribute("countCart");
			request.getSession().removeAttribute("cartPurchuse");
			return "redirect:/home/order";
		}
		
		
		return "redirect:/home/checkout";
		}catch(Exception e) {
			System.out.println(e);
			return "redirect:/home/checkout";
		}
	}

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
