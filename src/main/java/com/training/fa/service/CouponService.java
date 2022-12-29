package com.training.fa.service;

import java.util.List;

import com.training.fa.model.Coupon;
import com.training.fa.model.Coupon;

public interface CouponService {
	
	Coupon save(Coupon coupon);
	
	List<Coupon> getAllCoupon();
	
	//get 1 entity(khoa) thong qua id
	Coupon getCoupon(Integer id);
	
	//delete 1 doi tuong thong qua id
	void deleteCoupon(Integer id);
	
	//update 1 entity(khoa) 
	Coupon updateCoupon(Coupon coupon);
	
	Coupon findByuseValue(Integer useValue);

}
