package com.training.fa.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.fa.DTO.request.Cart;
import com.training.fa.DTO.response.JsonCode;
import com.training.fa.model.Coupon;
import com.training.fa.model.Product;
import com.training.fa.repository.ProductRepository;
import com.training.fa.service.CategoryService;
import com.training.fa.service.CouponService;
import com.training.fa.service.ProductService;

@Controller
@RequestMapping("/home")
public class ClientControllerCart {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CouponService couponService;
	

	@GetMapping("/add-cart")
	@ResponseBody
	public JsonCode addCart(Model model, @RequestParam("id") Integer id, HttpServletRequest request) {

		if (request.getSession().getAttribute("cartProduct") != null){
			List<Cart> cartSession = (List<Cart>) request.getSession().getAttribute("cartProduct");
			for (Cart item : cartSession) {
				if (item.getId() == id) {
					 JsonCode jsonCode = new JsonCode(400,"success");
						return jsonCode;
				}
			}
            
			Product product = productService.getProduct(id);
            Cart dtoCart = new Cart();
            dtoCart.setId(product.getId());
            dtoCart.setName(product.getName());
            dtoCart.setPrice(product.getPrice());
            dtoCart.setQuantity(1);
            dtoCart.setTotalPrice(product.getPrice()*dtoCart.getQuantity());
            dtoCart.setContent(product.getContent());
            dtoCart.setImagePath(product.getImagePath());
            
            
			cartSession.add(dtoCart);
            
			int countCart = cartSession.size();
			
			request.getSession().setAttribute("countCart", countCart);
			request.getSession().setAttribute("cartProduct", cartSession);
            JsonCode jsonCode = new JsonCode(200,"success");
			return jsonCode;
		}else {
			
			List<Product> products = new ArrayList<Product>();
			Product product1 = productService.getProduct(id);
			products.add(product1);
		
			List<Cart> dtoCarts = new ArrayList<Cart>();

			 for (Product product : products){
				    Cart dtoCart = new Cart();
		            dtoCart.setId(product.getId());
		            dtoCart.setName(product.getName());
		            dtoCart.setPrice(product.getPrice());
		            dtoCart.setQuantity(1);
		            dtoCart.setTotalPrice(product.getPrice()*dtoCart.getQuantity());
		            dtoCart.setContent(product.getContent());
		            dtoCart.setImagePath(product.getImagePath());
				 dtoCarts.add(dtoCart);
		        }
			
			int countCart = dtoCarts.size();
			
			request.getSession().setAttribute("countCart", countCart);
			request.getSession().setAttribute("cartProduct", dtoCarts);

			 JsonCode jsonCode = new JsonCode(200,"success");
				return jsonCode;
			

		}

		
	}
	
	@GetMapping("/shopping-cart")
	public String shoppingCart(Model model, HttpServletRequest request) {
		 request.getSession().removeAttribute("cartPurchuse");
		
		if(request.getSession().getAttribute("countCart")!=null && request.getSession().getAttribute("cartProduct")!=null) {
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");
			
			Integer sum = 0;
			
			for(Cart item:carts) {
				Integer total = item.getTotalPrice();
				sum += total;
			}
			
			if(request.getSession().getAttribute("counpon")==null ) {
				
				request.getSession().setAttribute("total_order", sum);
				System.out.println("khong");
				
			}else {
				
				Coupon counpon = (Coupon) request.getSession().getAttribute("counpon");
				Integer total_order = (int) (sum - (((Integer) sum*counpon.getUseValue()*0.01)));
				
				
				request.getSession().setAttribute("total_order", total_order);
				System.out.println("co");
				
			}
			
			request.getSession().setAttribute("sum", sum);
			
			List<Coupon> coupons = couponService.getAllCoupon();
			model.addAttribute("coupons", coupons);
			
			
			model.addAttribute("body", getBody("shopping-car"));
			return "index";
			
		}else {
		
		return "redirect:/home";
		}
	}

	
	@GetMapping("/number-cart")
	@ResponseBody
	public int showNumberCart(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("countCart")!=null) {
		int count = (int) request.getSession().getAttribute("countCart");
		
		return count;
		}else {
			return 0;
		}
	}
	
	@GetMapping("/update-cart")
	@ResponseBody
	public JsonCode updateCart(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity,HttpServletRequest request) {
		if(request.getSession().getAttribute("cartProduct")!=null) {
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");
			
			
			for(Cart item:carts) {
				
				if(item.getId()==id) {
					
					item.setQuantity(quantity);
					item.setTotalPrice(item.getPrice()*item.getQuantity());
					break;
				}
			}
			
			
		request.getSession().setAttribute("cartProduct", carts);
		JsonCode jsonCode = new JsonCode(200,"success");
		return jsonCode;
		
		}
		
		JsonCode jsonCode = new JsonCode(200,"success");
		return jsonCode;
		
	}
	
	
	@GetMapping("/delete-cart")
	@ResponseBody
	public JsonCode deleteCart(@RequestParam("id") Integer id,HttpServletRequest request) {
		
		List<Cart> carts = (List<Cart>) request.getSession().getAttribute("cartProduct");
		
		
		for(Cart item:carts) {
			if(item.getId()==id) {
				carts.remove(item);
				break;
			}
		}
		
		
		Integer sum = 0;
		
		for(Cart item:carts) {
			Integer total = item.getTotalPrice();
			sum += total;
		}
		
		request.getSession().setAttribute("sum", sum);
		
		request.getSession().removeAttribute("total_order");
		
		
		
		int countCart = carts.size();
		request.getSession().setAttribute("countCart", countCart);
		
		if(countCart==0) {
			request.getSession().removeAttribute("cartProduct");
		    

			JsonCode jsonCode = new JsonCode(200,"success");
			
			return jsonCode;
			
		}else {
			JsonCode jsonCode = new JsonCode(200,"success");
			
			request.getSession().setAttribute("cartProduct", carts);
			
			
			return jsonCode;
			
		}
		
		
	
		
	}
	
	
	

	@GetMapping("/check-coupon")
	@ResponseBody
	public JsonCode checkCoupon(@RequestParam("id") Integer id_coupon,HttpServletRequest request) {
		
		
		Integer sumCounpon =  (Integer) request.getSession().getAttribute("sum");
		
		Coupon counpon = couponService.getCoupon(id_coupon);
		if(sumCounpon > counpon.getTotal()) {
			
			Integer total_order = (Integer) ((Integer) request.getSession().getAttribute("sum")-((int) ((Integer) request.getSession().getAttribute("sum")*counpon.getUseValue()*0.01)));
			
			request.getSession().setAttribute("counpon", counpon);
			request.getSession().setAttribute("total_order", total_order);
			
			JsonCode jsonCode = new JsonCode(200,"success");
			return jsonCode;
			
		}else {
			JsonCode jsonCode = new JsonCode(400,"success");
			return jsonCode;
		}
		
		
		
	}
	
	@GetMapping("/checkShopping")
	@ResponseBody
	public JsonCode checkShopping(HttpServletRequest request) {
		if(request.getSession().getAttribute("cartProduct")==null) {
			JsonCode jsonCode = new JsonCode(200,"success");
			return jsonCode;
		}
		    JsonCode jsonCode = new JsonCode(400,"success");
			return jsonCode;
		
		
		
	}
	
	
	
	
	public static String getBody(String body) {
		return body + ".jsp";
	}

}
