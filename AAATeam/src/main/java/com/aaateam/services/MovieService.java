package com.aaateam.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.aaateam.models.entities.Booking;
import com.aaateam.models.entities.Movie;
import com.aaateam.models.entities.Schedule;

public interface MovieService {
	List<Movie> findByCategory(String category) throws Exception; 
	List<Movie> findAll() throws Exception;
	List<Schedule> findAllDay(Date date) throws Exception;
	void reservate(Timestamp timestamp, String username, Long code) throws Exception;
	List<Booking>showMovieReservation(String username) throws Exception;
	void deleteMovieReservation(String username, Long code) throws Exception;
	
}
