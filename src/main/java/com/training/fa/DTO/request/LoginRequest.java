package com.training.fa.DTO.request;

public class LoginRequest {
	
	private String username;
	  private String passwork;


	    public LoginRequest() {
		
	    }

	   public LoginRequest(String username, String passwork) {
		
		this.username = username;
		this.passwork = passwork;
	   }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", passwork=" + passwork + "]";
	}
	   
	   

}
