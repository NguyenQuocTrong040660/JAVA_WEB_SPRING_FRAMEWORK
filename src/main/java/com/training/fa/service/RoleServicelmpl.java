package com.training.fa.service;

import java.util.List;

import com.training.fa.model.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.fa.model.Role;
import com.training.fa.repository.RoleRepository;
@Service
public class RoleServicelmpl implements RoleService {
	  @Autowired
	    private RoleRepository roleRepository;



	   @Override
	    public List<Role> findAll() {
	        return roleRepository.findAll();
	    }
	    

}
