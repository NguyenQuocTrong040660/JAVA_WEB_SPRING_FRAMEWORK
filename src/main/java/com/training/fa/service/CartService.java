package com.training.fa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.training.fa.DTO.request.Cart;

public interface CartService {
	
	List<Cart> updateCart(List<Cart> carts, Integer quantity);
	
	List<Cart> saveCart(List<Cart> carts,HttpServletRequest request,Integer id);

}
