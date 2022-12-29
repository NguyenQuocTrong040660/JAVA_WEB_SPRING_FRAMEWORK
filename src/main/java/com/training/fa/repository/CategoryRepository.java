package com.training.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.fa.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
