package com.aaateam.services;

import java.util.List;

import com.aaateam.models.dtos.UserInfo;
import com.aaateam.models.entities.User;

public interface UserService {
	void register(UserInfo userInfo) throws Exception;
	User findOneByUsername(String username) throws Exception;
}
