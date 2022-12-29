package com.training.fa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Revenues {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String orderDate;
	
	private Integer sales;
	
	private Integer profit;
	
	private Integer totalOrder;

	public Revenues() {
		super();
	}

	public Revenues(String orderDate, Integer sales, Integer profit,  Integer totalOrder) {
		super();
		this.orderDate = orderDate;
		this.sales = sales;
		this.profit = profit;
		this.totalOrder = totalOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}


	public Integer getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Integer totalOrder) {
		this.totalOrder = totalOrder;
	}

	@Override
	public String toString() {
		return "Revenues [id=" + id + ", orderDate=" + orderDate + ", sales=" + sales + ", profit=" + profit
				+  ", totalOrder=" + totalOrder + "]";
	}
	
	
	

}
