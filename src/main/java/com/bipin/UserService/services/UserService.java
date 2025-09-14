package com.bipin.UserService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bipin.UserService.config.BcryptEncoder;
import com.bipin.UserService.dto.UserLoginDto;
import com.bipin.UserService.exception.PasswordNotFoundException;
import com.bipin.UserService.exception.UserNotFoundException;
import com.bipin.UserService.model.User;
import com.bipin.UserService.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BcryptEncoder bcryptEncoder;

	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = bcryptEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}

	public User findById(int id) {
		return null;
	}

	public User login(UserLoginDto dto) {
		User user = userRepo.findByEmail(dto.getEmail());
		if (user == null) {
			throw new UserNotFoundException("No user found with this email " + dto.getEmail());
		}

		if (bcryptEncoder.matches(dto.getPassword(), user.getPassword())) {
			return user;
		}
		else
			throw new PasswordNotFoundException("Wrong Password");

	}

	public List<User> findAll() {
		return null;
	}

	public List<User> findByRole(int roleId) {
		return null;
	}

}
