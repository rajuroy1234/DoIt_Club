package com.doit.social.controller;

import com.doit.social.model.User;
import com.doit.social.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody @Validated User user) {
		return service.save(user);
	}

	@GetMapping("/interest/{interest}")
	public List<User> getUsersByInterest(@PathVariable String interest) {
		return service.findByInterest(interest);
	}

	@GetMapping("/city/{city}")
	public List<User> getUsersByCity(@PathVariable String city) {
		return service.findByCity(city);
	}
}
