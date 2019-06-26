package com.classproject.service;

import com.classproject.usersandroles.User;

public interface UserService {

	public String registerUser(User user);
	
	public User findByEmail(String email);
	
	public String userActivation(String code);
}
