package com.training.fa.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="counpon")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer useValue;
	
	private String desCription;
	
	private Integer total;
	
	
	@OneToMany(mappedBy = "coupon",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	private List<Order>  Orders;


	public Coupon() {
		super();
	}

	

	
	




	public Coupon( Integer useValue, String desCription, Integer total) {
		super();
		
		this.useValue = useValue;
		this.desCription = desCription;
		this.total = total;
	}



	public Coupon(Integer id, Integer useValue, String desCription, Integer total,
			List<Order> orders) {
		super();
		this.id = id;
		
		this.useValue = useValue;
		this.desCription = desCription;
		this.total = total;
		Orders = orders;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getUseValue() {
		return useValue;
	}



	public void setUseValue(Integer useValue) {
		this.useValue = useValue;
	}



	public String getDesCription() {
		return desCription;
	}



	public void setDesCription(String desCription) {
		this.desCription = desCription;
	}



	public Integer getTotal() {
		return total;
	}



	public void setTotal(Integer total) {
		this.total = total;
	}



	public List<Order> getOrders() {
		return Orders;
	}



	public void setOrders(List<Order> orders) {
		Orders = orders;
	}



	@Override
	public String toString() {
		return "Coupon [id=" + id + ", useValue=" + useValue + ", desCription=" + desCription + ", total=" + total
				+ ", Orders=" + Orders + "]";
	}



	
	
	
	
	
	
	
	

}
