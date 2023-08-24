package com.examly.springapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.User;
import com.examly.springapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public String passwordEncoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public User registerUser(User user) {
		user.setPassword(passwordEncoder(user.getPassword()));
		User newUser= userRepo.save(user);
		return newUser;
	}
	
	public String loginUser(User user)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User u1= userRepo.findByUsername(user.getUsername());
		//user.setPassword(passwordEncoder(user.getPassword()));
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		System.out.println(u1.getPassword());
		System.out.println(encodedPassword);
		boolean isPasswordMatch = passwordEncoder.matches(u1.getPassword(), encodedPassword);
		if(isPasswordMatch)
		{
			return u1.getRole(); 
		}
		return "failed"; 
	}

}
