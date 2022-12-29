package com.training.fa.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.training.fa.model.Order;
import com.training.fa.model.Product;
import com.training.fa.model.User;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT e FROM Order e")
	Page<Order> findOrders(Pageable pageable);
	
	
	Page<Order> findByOrderStatus(Integer orderstatus,Pageable pageable);
	
	
	Page<Order> findByUser(User user,Pageable pageable);
	
	Page<Order> findByUserAndOrderStatus(User user,Integer orderstatus,Pageable pageable);
	
	
	Page<Order> findByUserAndOrderStatusAndCreateTimeContaining(User user,Integer orderstatus,String createTime,Pageable pageable);
	
	
	@Modifying
	@Query("SELECT e FROM Order e  where e.orderStatus = 0")
	List<Order> findOrderByStatus0();
	
	
	@Query("SELECT e FROM Order e  where e.orderStatus = 1")
	List<Order> findOrderByStatus1();
	
	
	@Modifying
	@Query("update Order ear set ear.orderStatus = :orderStatus where ear.id = :id")
	int setStatusForOrders(@Param("orderStatus") Integer orderStatus, @Param("id") Integer id);

}
