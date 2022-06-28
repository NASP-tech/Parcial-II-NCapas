package com.aaateam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaateam.models.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, String>{
	Category findOneByCode(String code);
}
