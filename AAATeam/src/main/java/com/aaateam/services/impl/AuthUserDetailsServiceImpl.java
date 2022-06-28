package com.aaateam.services.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aaateam.models.dtos.UserInfo;
import com.aaateam.models.entities.User;
import com.aaateam.repositories.UserRepository;
import com.aaateam.services.UserService;


@Service
public class AuthUserDetailsServiceImpl implements UserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(UserInfo userInfo) throws Exception {
		User user = new User();
		
		String encryptedPassword = passwordEncoder.encode(userInfo.getPassword());
		
		user.setUsername(userInfo.getUsername());
		user.setEmail(userInfo.getEmail());
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
		
	}

	@Override
	public User findOneByUsername(String username) throws Exception {
		User foundUser = userRepository
				.findOneByUsername(username);
		return foundUser;
	}
	
	
}
