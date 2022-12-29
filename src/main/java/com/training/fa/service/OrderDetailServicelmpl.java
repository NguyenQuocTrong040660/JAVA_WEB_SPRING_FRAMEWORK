package com.training.fa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.training.fa.model.OrderDetail;
import com.training.fa.repository.OrderDetailRepository;

@Service
public class OrderDetailServicelmpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	
	@Override
	public List<OrderDetail> getAllOrderDetail() {
		
		return orderDetailRepository.findAll();
	}

	@Override
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		try {
		return orderDetailRepository.save(orderDetail);
		
		}catch(Exception e) {
			System.out.println("Loi save orderdetail"+e);
			
			return orderDetailRepository.save(orderDetail);
		}
		
	}

	@Override
	public OrderDetail getOrderDetail(Integer id) {
		 Optional<OrderDetail> orderDetailOption = orderDetailRepository.findById(id);
		 if(orderDetailOption.isPresent()) {
			 return orderDetailOption.get();
		 }
			throw new RuntimeException("not find by id entity OrderDetail!!");
		
	}

	@Override
	public void deleteOrderDetail(Integer id) {
		orderDetailRepository.deleteById(id);

	}

	@Override
	public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
		
		return orderDetailRepository.save(orderDetail);
	}

	@Override
	public List<OrderDetail> findByOrderID(Integer id) {
		
		return orderDetailRepository.findByOrderId(id);
	}

	

	

}
