package com.aaateam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaateam.models.entities.Category;
import com.aaateam.models.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{
	List<Movie> findByCategory(Category category);
}
