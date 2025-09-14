package com.bipin.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bipin.UserService.dto.UserLoginDto;
import com.bipin.UserService.model.User;
import com.bipin.UserService.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(service.save(user));
	}

	@GetMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto dto) {
		return ResponseEntity.ok(service.login(dto));
	}

}
