package com.training.fa.service;

import java.util.Date;
import java.util.List;

import com.training.fa.model.Revenues;

public interface RevenuesService {
	        //get ds Revenues
			List<Revenues> getAllRevenues();
			
			//save 1 entity(khoa)
			Revenues saveRevenues(Revenues revenues);
			
			//get 1 entity(khoa) thong qua id
			Revenues getRevenues(Integer id);
			
			//delete 1 doi tuong thong qua id
			void deleteRevenues(Integer id);
			
			//update 1 entity(khoa) 
			Revenues updateRevenues(Revenues revenues);
			
			Revenues findByOrderDate(String orderDate);
			
			List<Revenues> findByOrderDateBetween(String from_date, String to_date);
			
			//Increate revenes in new month with old month
			List<Revenues> findIncreaVenues(String curent_date);
	
	

}
