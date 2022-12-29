package com.training.fa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.fa.model.Coupon;
import com.training.fa.repository.CouponRepository;
import com.training.fa.service.CouponService;

@Controller
@RequestMapping("/admin")
public class AdminControllerCoupon {
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/coupons")
	public String counpons(Model model) {
		
		List<Coupon> coupons = couponService.getAllCoupon();
		model.addAttribute("coupons", coupons);
		
		model.addAttribute("body", getBody("./coupon/list-coupon"));
		return "admin/index";
	}
	
	
	@PostMapping("/coupons")
	public String counponSave(Model model,
			@RequestParam("useValue") Integer useValue,
			@RequestParam("desCription") String desCription,
			@RequestParam("total") Integer total) {
		Coupon coupon = new Coupon(useValue,desCription,total);
		couponService.save(coupon);
		
		List<Coupon> coupons = couponService.getAllCoupon();
		model.addAttribute("coupons", coupons);
		model.addAttribute("body", getBody("./coupon/list-coupon"));
		
		
		
		return "admin/index";
	}
	
	
	@GetMapping("/delete-coupon")
	public String deleteCoupon(Model model,@RequestParam("id") Integer id) {
		
		couponService.deleteCoupon(id);
		
		return "redirect:/admin/coupons";
	}
	
	
	
	
	
	public static String getBody(String body) {
		return body+".jsp";
	}

}
