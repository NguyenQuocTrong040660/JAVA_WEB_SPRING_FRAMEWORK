package com.training.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.fa.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

}
