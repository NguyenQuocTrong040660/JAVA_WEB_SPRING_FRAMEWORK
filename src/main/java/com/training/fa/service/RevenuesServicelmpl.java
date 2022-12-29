package com.training.fa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.fa.model.Revenues;
import com.training.fa.repository.RevenuesRepository;

@Service
public class RevenuesServicelmpl implements RevenuesService {

	@Autowired
	private RevenuesRepository revenuesRepository;
	
	@Override
	public List<Revenues> getAllRevenues() {
		
		return revenuesRepository.findAll();
	}

	@Override
	public Revenues saveRevenues(Revenues revenues) {
		
		return revenuesRepository.save(revenues);
	}

	@Override
	public Revenues getRevenues(Integer id) {
		
		return null;
	}

	@Override
	public void deleteRevenues(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Revenues updateRevenues(Revenues revenues) {
		
		return revenuesRepository.save(revenues);
	}

	@Override
	public Revenues findByOrderDate(String orderDate) {
		
		return revenuesRepository.findByOrderDate(orderDate);
	}

	@Override
	public List<Revenues> findByOrderDateBetween(String from_date, String to_date) {
		
		return revenuesRepository.findByOrderDateBetweenOrderByOrderDateAsc(from_date, to_date);
	}

	@Override
	public List<Revenues> findIncreaVenues(String curent_date) {
		
		return revenuesRepository.findIncreaVenues(curent_date);
	}

}
