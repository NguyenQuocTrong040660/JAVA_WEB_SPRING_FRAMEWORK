package com.training.fa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private Integer price;
	
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Order order;
	
	
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="create_at", nullable=false ,updatable=false)
	private Date createTime;
	
	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="update_at")
	private Date updateTime;

	
	
	
	public OrderDetail() {
		super();
	}
	
	
	

	public OrderDetail(Integer price, Integer quantity, Product product, Order order) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}




	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", price=" + price + ", quantity=" + quantity + ", product=" + product
				+ ", order=" + order + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	
	
	
	
	
	
	
}
