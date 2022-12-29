package com.training.fa.service;

import java.util.List;

import com.training.fa.model.Shipping;

public interface ShippingService {
	    //get ds Shipping
			List<Shipping> getAllShipping();
			
			//save 1 entity(khoa)
			Shipping saveShipping(Shipping shipping);
			
			//get 1 entity(khoa) thong qua id
			Shipping getShipping(Integer id);
			
			//delete 1 doi tuong thong qua id
			void deleteShipping(Integer id);
			
			//update 1 entity(khoa) 
			Shipping updateShipping(Shipping shipping);
			
			
			

}
