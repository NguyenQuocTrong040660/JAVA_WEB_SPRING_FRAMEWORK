package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.training.fa.model.Order;
import com.training.fa.model.User;

public interface OrderService {
List<Order> getAllOrder();
	
	//save 1 entity(khoa)
	Order saveOrder(Order order);
	
	//get 1 entity(khoa) thong qua id
	Order getOrder(Integer id);
	
	//delete 1 doi tuong thong qua id
	void deleteOrder(Integer id);
	
	//update 1 entity(khoa) 
	Order updateOrder(Order order);
	//update Status entity(khoa) 
	
	List<Order> findOrderByStatus1();
	
	Page<Order> findByorderStatus(Integer orderstatus,Pageable pageable);
	
    Page<Order> findByUser(User user,Pageable pageable);
    
    Page<Order> findByUserAndOrderStatus(User user,Integer orderStatus,Pageable pageable);
    
    
	

}
