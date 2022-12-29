package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.fa.model.Category;
import com.training.fa.model.User;
import com.training.fa.repository.UserRepository;

@Service
public class UserServicelmpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


	
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Long id) {
		 Optional<User> userOption = userRepository.findById(id);
		 if(userOption.isPresent()) {
			 return userOption.get();
		 }
			throw new RuntimeException("not find by id entity user!!");
		
	}

	

	@Override
	public User updateUser(User User) {
		// TODO Auto-generated method stub
		return userRepository.save(User);
	}




	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}
	
	

}
