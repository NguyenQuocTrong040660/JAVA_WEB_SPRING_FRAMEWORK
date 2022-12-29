package com.training.fa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.training.fa.model.User;

public interface UserService {

	  //get ds User
		List<User> getAllUser();
		
		//save 1 entity(khoa)
		User saveUser(User user);
		
		//get 1 entity(khoa) thong qua id
		User getUser(Long id);
		
		
		
		//update 1 entity(khoa) 
		User updateUser(User User);
		
		Page<User> findAll(Pageable pageable);
		void deleteById(Long id);
		
		
		
}
