package com.aaateam.services.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaateam.models.entities.Booking;
import com.aaateam.models.entities.Category;
import com.aaateam.models.entities.Movie;
import com.aaateam.models.entities.Schedule;
import com.aaateam.models.entities.User;
import com.aaateam.repositories.BookingRepository;
import com.aaateam.repositories.CategoryRepository;
import com.aaateam.repositories.MovieRepository;
import com.aaateam.repositories.ScheduleRepository;
import com.aaateam.repositories.UserRepository;
import com.aaateam.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Movie> findByCategory(String category) throws Exception {
		Category foundCategory = categoryRepository.findOneByCode(category);
		
		List<Movie> foundMovies = movieRepository
				.findByCategory(foundCategory);
		
		return foundMovies;
	}

	@Override
	public List<Movie> findAll() throws Exception {
		return movieRepository.findAll();
	}

	@Override
	public List<Schedule> findAllDay(Date date) throws Exception {
		List<Schedule> foundSchedule = scheduleRepository
				.findByTimestamp(date);
		return foundSchedule;
	}

	@Override
	public void reservate(Timestamp timestamp, String username, Long code) throws Exception {
		Booking booking = new Booking();
		User user = userRepository.findOneByUsername(username);
		Schedule schedule = scheduleRepository.findOneById(code);
		
		booking.setSchedule(schedule);
		booking.setTimestamp(timestamp);
		booking.setUser(user);
		
		bookingRepository.save(booking);
		
	}

	@Override
	public List<Booking> showMovieReservation(String username) throws Exception {
		User user = userRepository.findOneByUsername(username);
		List<Booking> foundBooking = bookingRepository.findByUser(user);
		return foundBooking;
	}

	@Override
	public void deleteMovieReservation(String username, Long code) throws Exception {
		User user = userRepository.findOneByUsername(username);
		Schedule schedule = scheduleRepository.findOneById(code);
		Booking booking = bookingRepository.findOneByUserAndSchedule(user, schedule);
		bookingRepository.delete(booking);
	}

	
	
}
