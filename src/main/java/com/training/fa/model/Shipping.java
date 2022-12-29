package com.training.fa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Shipping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipping_id")
	private Integer id;
	
	@Column(name="shipping_name")
	private String shippingName;
	
	@Column(name="shipping_phone")
	private String shippingPhone;
	
	@Column(name="shipping_Address")
	private String shippingAddress;
	
	@Column(name="shipping_note")
	private String shippingNote;
	
	@OneToMany(mappedBy = "shipping")
	List<Order> orders;

	
	public Shipping() {
		super();
	}


	
	public Shipping(String shippingName, String shippingPhone, String shippingAddress, String shippingNote
			) {
		super();
		this.shippingName = shippingName;
		this.shippingPhone = shippingPhone;
		this.shippingAddress = shippingAddress;
		this.shippingNote = shippingNote;
		
	}



	public Shipping( String shippingName, String shippingPhone, String shippingAddress, List<Order> orders) {
		super();
		
		this.shippingName = shippingName;
		this.shippingPhone = shippingPhone;
		this.shippingAddress = shippingAddress;
		this.orders = orders;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getShippingName() {
		return shippingName;
	}


	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}


	public String getShippingPhone() {
		return shippingPhone;
	}


	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	


	public String getShippingNote() {
		return shippingNote;
	}



	public void setShippingNote(String shippingNote) {
		this.shippingNote = shippingNote;
	}



	@Override
	public String toString() {
		return "Shipping [id=" + id + ", shippingName=" + shippingName + ", shippingPhone=" + shippingPhone
				+ ", shippingAddress=" + shippingAddress + ", shippingNote=" + shippingNote + ", orders=" + orders
				+ "]";
	}



	
	
	
	
	
	
	
	
	

}
