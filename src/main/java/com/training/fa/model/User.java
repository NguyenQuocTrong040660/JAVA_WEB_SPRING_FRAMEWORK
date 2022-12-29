package com.training.fa.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tbl_users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id") // map den truong"id"(filed) trong csdl
	   private Long id;
	
	@Column(name="username",columnDefinition = "nvarchar(200)")
	@NotBlank(message = "username khong duoc trong NotBlank")
	private String username;
	
	@Column(name="email")
	@NotBlank(message = "email khong duoc trong NotBlank")
	@Email
	private String email;
	
	@Column(name="passwork")
	@NotBlank(message = "passwork khong duoc trong NotBlank")
	private String passwork;
	

	
	// Relationship
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	              joinColumns = @JoinColumn(name="user_id"),
	              inverseJoinColumns = @JoinColumn(name="role_id"))
	 private Set<Role> roles = new HashSet<>();
	
	

	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	

	
	//contructor
	public User() {
		
	}
	
	public User(String username, String email,
			 String passwork) {
		
		this.username = username;
		this.email = email;
		this.passwork = passwork;
		
	}
	
	



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPasswork() {
		return passwork;
	}



	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", passwork=" + passwork + ", roles="
				+ roles + ", orders=" + orders + "]";
	}

	

}
