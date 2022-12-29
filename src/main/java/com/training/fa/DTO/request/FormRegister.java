package com.training.fa.DTO.request;

import java.util.Set;

public class FormRegister {

	private String username;
	private String email;
	private String passwork;
	private Set<String> role;
	
	
	
	public FormRegister() {
		super();
	}
	public FormRegister(String username, String email, String passwork, Set<String> role) {
		super();
		this.username = username;
		this.email = email;
		this.passwork = passwork;
		this.role = role;
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
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	
	
	
	
	
}
