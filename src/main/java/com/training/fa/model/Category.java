package com.training.fa.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Category {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    
	private String name;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private List<Product> products;
	
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="create_at", nullable=false ,updatable=false)
	private Date createTime;
	
	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="MM/dd/yyyy")
	@Column(name="update_at")
	private Date updateTime;
	
	
	
	

	public Category() {
		super();
	}
	
	

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Category(Integer id, String name, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
		return "Category [id=" + id + ", name=" + name + ", products=" + products + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	
	
	
	

}
