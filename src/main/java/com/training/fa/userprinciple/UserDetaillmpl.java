package com.training.fa.userprinciple;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.fa.model.Role;
import com.training.fa.model.User;

public class UserDetaillmpl implements UserDetails {
	
	private Long id;
	private String username;
	private String email;
	
	@JsonIgnore 
	private String password;
	
    private Collection<?  extends GrantedAuthority> role;
    
    
	
	public UserDetaillmpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> role) {
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	

	//xay dung build
	public static UserDetaillmpl build(User user) {
		
		/*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    Set<Role> roles = user.getRoles();
	    for (Role role : roles) {
	    	
	        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().name()));
	    }*/
	    
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(roles->new SimpleGrantedAuthority(roles.getName().name()))
				.collect(Collectors.toList());
		return new UserDetaillmpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(), 
				user.getPasswork(),
				authorities);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return role;
	}

	@Override
	public String getPassword() {
		
		return password;
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetaillmpl user = (UserDetaillmpl) o;
		return Objects.equals(id, user.id);
	}



	@Override
	public String toString() {
		return "UserDetaillmpl [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Collection<? extends GrantedAuthority> getRole() {
		return role;
	}



	public void setRole(Collection<? extends GrantedAuthority> role) {
		this.role = role;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
