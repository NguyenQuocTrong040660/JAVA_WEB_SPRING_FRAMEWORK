package com.training.fa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.fa.model.Product;
import com.training.fa.model.Shipping;
import com.training.fa.repository.ShippingRepository;

@Service
public class ShippingServicelmpl implements ShippingService {
   
    @Autowired
    private ShippingRepository shippingRepository;
	
	@Override
	public List<Shipping> getAllShipping() {
		
		return shippingRepository.findAll();
	}

	@Override
	public Shipping saveShipping(Shipping shipping) {
		
		return shippingRepository.save(shipping);
	}

	@Override
	public Shipping getShipping(Integer id) {
		
		 Optional<Shipping> shippingOption = shippingRepository.findById(id);
		 if(shippingOption.isPresent()) {
			 return shippingOption.get();
		 }
			throw new RuntimeException("not find by id entity Shipping!!");
		
	}

	@Override
	public void deleteShipping(Integer id) {
		shippingRepository.deleteById(id);

	}

	@Override
	public Shipping updateShipping(Shipping shipping) {
		
		return shippingRepository.save(shipping);
	}

}
