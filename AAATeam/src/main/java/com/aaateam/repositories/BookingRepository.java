package com.aaateam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaateam.models.entities.Booking;
import com.aaateam.models.entities.Schedule;
import com.aaateam.models.entities.User;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findByUser(User user);
	Booking findOneByUserAndSchedule(User user, Schedule schedule);	
}
