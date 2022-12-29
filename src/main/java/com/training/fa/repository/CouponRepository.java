package com.training.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.fa.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	Coupon findByUseValue(Integer useValue);

}
