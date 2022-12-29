package com.training.fa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.fa.model.Revenues;

public interface RevenuesRepository extends JpaRepository<Revenues, Integer>{
	
	public Revenues findByOrderDate(String orderDate);
	

	List<Revenues> findByOrderDateBetweenOrderByOrderDateAsc(String from_date, String to_date);
	
	@Query("select a from Revenues a  where DATEDIFF(month,a.orderDate,?1)=0")
	List<Revenues> findIncreaVenues(String curent_date);

}
