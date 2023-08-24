package com.examly.springapp.services;

import com.examly.springapp.entities.User;


public interface UserService {

	public User registerUser(User user);
	public String loginUser(User user);

}
