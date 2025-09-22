package com.bipin.UserService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bipin.UserService.client.KafkaProducerClient;
import com.bipin.UserService.config.BcryptEncoder;
import com.bipin.UserService.dto.EmailDto;
import com.bipin.UserService.dto.UserLoginDto;
import com.bipin.UserService.exception.PasswordNotFoundException;
import com.bipin.UserService.exception.UserNotFoundException;
import com.bipin.UserService.model.User;
import com.bipin.UserService.repo.UserRepo;
import com.bipin.UserService.security.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private BcryptEncoder bcryptEncoder;

	@Autowired
	KafkaProducerClient kafkaProducerClient;

	@Autowired
	ObjectMapper objectMapper;

	public String save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = bcryptEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		User savedUser = userRepo.save(user);
		String token = jwtService.generateToken(user.getEmail());

		try {
			// Send welcome email to the user
			EmailDto emailDto = new EmailDto();
			emailDto.setTo(user.getEmail());
			emailDto.setFrom("anuragonhiring@gmail.com");
			emailDto.setSubject("Welcome Onboard!!");
			emailDto.setBody("Your account has been created successfully!!");
			String message = objectMapper.writeValueAsString(emailDto);
			kafkaProducerClient.sendMessage("signup", message);
		} catch (JsonProcessingException exception) {
			throw new RuntimeException(exception.getMessage());
		}
		return token;
	}

	public User findById(int id) {
		return null;
	}

	public String login(UserLoginDto dto) {
		User user = userRepo.findFirstByEmail(dto.getEmail());
		if (user == null) {
			throw new UserNotFoundException("No user found with this email " + dto.getEmail());
		}

		if (bcryptEncoder.matches(dto.getPassword(), user.getPassword())) {
			String token = jwtService.generateToken(user.getEmail());
			return token;
		} else
			throw new PasswordNotFoundException("Wrong Password");

	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public List<User> findByRole(int roleId) {
		return null;
	}

}
