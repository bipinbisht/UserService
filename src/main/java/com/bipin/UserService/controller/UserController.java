package com.bipin.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bipin.UserService.model.User;
import com.bipin.UserService.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

}
