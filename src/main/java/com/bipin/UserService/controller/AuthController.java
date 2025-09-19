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
public class AuthController {

	@Autowired
	private UserService service;

	@PostMapping("auth/register")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		return ResponseEntity.ok(service.save(user));
	}

	@GetMapping("auth/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDto dto) {
		return ResponseEntity.ok(service.login(dto));
	}

}
