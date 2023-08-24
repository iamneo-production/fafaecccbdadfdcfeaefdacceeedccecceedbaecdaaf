package com.examly.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.User;
import com.examly.springapp.services.UserService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://8080-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody User user) {

		User s = userService.registerUser(user);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/auth/login")
	public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
		// Hardcoded user credentials for demonstration purposes
		User admin = new User(1L, "admin", "admin123", "ADMIN");
		User organizer = new User(1L, "organizer", "organizer123", "ORGANIZER");

		// Check if the provided credentials match any hardcoded user
		if (loginUser.getUsername().equals(admin.getUsername())
				&& loginUser.getPassword().equals(admin.getPassword())) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else if (loginUser.getUsername().equals(organizer.getUsername())
				&& loginUser.getPassword().equals(organizer.getPassword())) {
			return new ResponseEntity<>(organizer, HttpStatus.OK);
		}

		// Return null if the provided credentials don't match any user
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	

}
