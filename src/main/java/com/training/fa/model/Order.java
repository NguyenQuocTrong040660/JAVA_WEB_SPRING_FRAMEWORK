package com.training.fa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer id;

	@Column(name="order_status")
	private Integer orderStatus;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="shipping_id",nullable = false)
	private Shipping shipping;
	
	@ManyToOne
	@JoinColumn(name="coupon_id",nullable = false)
	private Coupon coupon;
	

	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderDetail>  orderDetails;
	
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="create_at", nullable=false ,updatable=false)
	private Date createTime;
	
	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="update_at")
	private Date updateTime;
	
	
	public Order() {
		super();
	}
	
	
 

	



	public Order(Integer id, Integer orderStatus, User user, Shipping shipping, Coupon coupon) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.user = user;
		this.shipping = shipping;
		this.coupon = coupon;
	}








	public Order( Integer orderStatus, User user, Shipping shipping, Coupon coupon) {
		super();
		
		this.orderStatus = orderStatus;
		this.user = user;
		this.shipping = shipping;
		this.coupon=coupon;
	}
	
	public Order(Integer orderStatus, User user, Shipping shipping, Coupon coupon, List<OrderDetail> orderDetails) {
		super();
		this.orderStatus = orderStatus;
		this.user = user;
		this.shipping = shipping;
		this.coupon = coupon;
		this.orderDetails = orderDetails;
	}






	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Shipping getShipping() {
		return shipping;
	}


	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}


	
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
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
	
	


	public Coupon getCoupon() {
		return coupon;
	}




	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", orderStatus=" + orderStatus + ", user=" + user + ", shipping=" + shipping
				+ ", coupon=" + coupon + ", orderDetails=" + orderDetails + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}






	

	

    
	
	
	
	
	
	
	
	
	
	

}
