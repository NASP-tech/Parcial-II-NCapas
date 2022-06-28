package com.aaateam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaateam.models.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//User findOneByUsernameOrEmail(String username, String email);
	User findOneByUsername(String username);
}
