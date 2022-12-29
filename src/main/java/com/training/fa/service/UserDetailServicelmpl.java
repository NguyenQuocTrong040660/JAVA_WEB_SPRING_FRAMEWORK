package com.training.fa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.fa.model.User;
import com.training.fa.repository.UserRepository;
import com.training.fa.userprinciple.UserDetaillmpl;

@Service
public class UserDetailServicelmpl implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;
	
	@Transactional
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		    User user =  UserRepository .findByUsername(username)
		    		.orElseThrow(()->new UsernameNotFoundException("username not found in DB with"+username));
		return UserDetaillmpl.build(user);
	}

}
