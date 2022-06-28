package com.aaateam.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaateam.models.dtos.BookingDTO;
import com.aaateam.models.dtos.BookingInfo;
import com.aaateam.models.dtos.MessageDTO;
import com.aaateam.models.dtos.UserInfo;
import com.aaateam.models.entities.Booking;
import com.aaateam.models.entities.Category;
import com.aaateam.models.entities.Movie;
import com.aaateam.models.entities.Schedule;
import com.aaateam.models.entities.User;
import com.aaateam.services.MovieService;
import com.aaateam.services.UserService;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
	@Autowired
	private MovieService movieService;	
	
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> findAllMovies(){
		try {
			List<Movie> movies = movieService.findAll();
			
			return new ResponseEntity<>(
					movies,
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	
	@GetMapping("/movies/{category}")
	public ResponseEntity<List<Movie>> getMoviesCategory(@PathVariable("category") String category){
		try {
			List<Movie> movies = movieService.findByCategory(category);
			if(movies == null) {
				return new ResponseEntity<>(
						null, 
						HttpStatus.NOT_FOUND
						);
			}
			return new ResponseEntity<>(
					movies,
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	
	@GetMapping("/today")
	public ResponseEntity<List<Schedule>> findAllCategoriesToday(){
		try {
			Date date = new Date(System.currentTimeMillis());
			List<Schedule> schedule = movieService.findAllDay(date);
			
			return new ResponseEntity<>(
					schedule,
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	
	@PostMapping("/reserve")
	public ResponseEntity<MessageDTO> registerReserve(@Valid BookingInfo bookingInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String error = result.getAllErrors().toString();
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + error),
						HttpStatus.BAD_REQUEST
				);
			}
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			movieService.reservate(timestamp, bookingInfo.getUsername(), Long.parseLong(bookingInfo.getCode()));
			
			return new ResponseEntity<>(
					new MessageDTO("Registro completo"),
					HttpStatus.CREATED
					);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(
				new MessageDTO("Hay errores Interno "),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	
	@PostMapping("/booking")
	public ResponseEntity<List<Booking>> getAllBooking(@Valid BookingDTO showReservation, BindingResult result){
		try {
			if(result.hasErrors()) {
				String error = result.getAllErrors().toString();
				
				return new ResponseEntity<>(
						null,
						HttpStatus.BAD_REQUEST
						);
			}
			List<Booking> bookings = movieService.showMovieReservation(showReservation.getUsername());
			
			return new ResponseEntity<>(
					bookings,
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
			
	
	

    @DeleteMapping(value = "/delete")
    public ResponseEntity<MessageDTO> deletePost(@Valid BookingInfo bookingInfo, BindingResult result) {

        try {
			if(result.hasErrors()) {
				String error = result.getAllErrors().toString();
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " +  error),
						HttpStatus.BAD_REQUEST
						);
			}
			movieService.deleteMovieReservation(bookingInfo.getUsername(), Long.parseLong(bookingInfo.getCode()));
			
			return new ResponseEntity<>(
					new MessageDTO("Reservacion Eliminada con Exito "),
					HttpStatus.CREATED
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error Interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
    }
	
}
