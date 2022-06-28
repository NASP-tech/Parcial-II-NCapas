package com.aaateam.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aaateam.models.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	@Query("SELECT sch FROM schedule sch where DATE(timestamp) = :date")
	List<Schedule> findByTimestamp(@Param("date") Date date);
	Schedule findOneById(Long id);
	
}
