package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.fa.model.Order;
import com.training.fa.model.Shipping;
import com.training.fa.model.User;
import com.training.fa.repository.OrderRepository;

@Service
public class OrderServicelmpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrder() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		
		return orderRepository.save(order);
	}

	@Override
	public Order getOrder(Integer id) {
		

		 Optional<Order> OrderOption = orderRepository.findById(id);
		 if(OrderOption.isPresent()) {
			 return OrderOption.get();
		 }
			throw new RuntimeException("not find by id entity Order!!");
		
	}

	@Override
	public void deleteOrder(Integer id) {
		orderRepository.deleteById(id);

	}

	@Override
	public Order updateOrder(Order order) {
		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findOrderByStatus1() {
		
		return orderRepository.findOrderByStatus1();
	}

	@Override
	public Page<Order> findByorderStatus(Integer orderstatus, Pageable pageable) {
		// TODO Auto-generated method stub
		return orderRepository.findByOrderStatus(orderstatus, pageable);
	}
	
	@Override
	public Page<Order> findByUser(User user,Pageable pageable) {
		// TODO Auto-generated method stub
		return orderRepository.findByUser(user, pageable);
	}

	@Override
	public Page<Order> findByUserAndOrderStatus( User user,Integer orderStatus, Pageable pageable) {
		
		return orderRepository.findByUserAndOrderStatus(user, orderStatus, pageable);
	}

	

}
