package com.bipin.UserService.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthProviderConfig {

	private final AppUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public AuthProviderConfig(AppUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {

		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
}
