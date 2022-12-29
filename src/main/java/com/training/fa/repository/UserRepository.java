package com.training.fa.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.fa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	   //Tim kiem user have exits in DB ??
		Optional<User> findByUsername(String username);
		
		//Kiem tra user co ton tai csdl ko?
		Boolean existsByUsername(String username);
		
		//Kiem tra email co ton tai csdl ko?
		Boolean existsByEmail(String email);
		
        
		Page<User> findAll(Pageable pageable);

}
