package com.bipin.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;
	private final AuthenticationProvider authenticationProvider;
	private final RestAuthEntryPoint authEntryPoint;

	// constructor-based dependency injection
	public SecurityConfig(JwtAuthenticationFilter jwtFilter, AuthenticationProvider authenticationProvider,
			RestAuthEntryPoint authEntryPoint) {
		this.jwtFilter = jwtFilter;
		this.authenticationProvider = authenticationProvider;
		this.authEntryPoint = authEntryPoint;
	}

	// configure security filter chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/login", "/auth/register").permitAll()
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
}
