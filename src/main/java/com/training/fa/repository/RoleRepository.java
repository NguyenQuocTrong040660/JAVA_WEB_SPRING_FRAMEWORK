package com.training.fa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.fa.model.ERole;
import com.training.fa.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	//Tim  trong csdl co Role do Khong => optional
    Optional<Role> findByName(ERole name);
	

}
