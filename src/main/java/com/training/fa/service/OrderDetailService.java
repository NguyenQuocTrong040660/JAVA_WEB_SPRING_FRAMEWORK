package com.training.fa.service;

import java.util.List;

import com.training.fa.model.OrderDetail;

public interface OrderDetailService {
   List<OrderDetail> getAllOrderDetail();
	
   
	//save 1 entity(khoa)
	OrderDetail saveOrderDetail(OrderDetail orderDetail);
	
	//get 1 entity(khoa) thong qua id
	OrderDetail getOrderDetail(Integer id);
	
	//delete 1 doi tuong thong qua id
	void deleteOrderDetail(Integer id);
	
	//update 1 entity(khoa) 
	OrderDetail updateOrderDetail(OrderDetail orderDetail);
	
	
	List<OrderDetail> findByOrderID(Integer id);
	
	
}
