package com.bipin.UserService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BcryptEncoder implements PasswordEncoder {

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public String encode(CharSequence rawPassword) {

		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

}
