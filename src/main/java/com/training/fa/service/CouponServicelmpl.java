package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.fa.model.Category;
import com.training.fa.model.Coupon;
import com.training.fa.repository.CouponRepository;

@Service
public class CouponServicelmpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	public Coupon save(Coupon coupon) {
		
		return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> getAllCoupon() {
		
		return couponRepository.findAll();
	}

	@Override
	public Coupon getCoupon(Integer id) {
		
		 Optional<Coupon> couponOption = couponRepository.findById(id);
		 if(couponOption.isPresent()) {
			 return couponOption.get();
		 }
			throw new RuntimeException("not find by id entity Coupon!!");
		
	}

	@Override
	public void deleteCoupon(Integer id) {
		
		couponRepository.deleteById(id);
		
		
	}

	@Override
	public Coupon updateCoupon(Coupon coupon) {
		
		return null;
	}

	@Override
	public Coupon findByuseValue(Integer useValue) {
		
		return couponRepository.findByUseValue(useValue);
	}

}
