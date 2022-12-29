package com.training.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.fa.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrderId(Integer order_id);

}
