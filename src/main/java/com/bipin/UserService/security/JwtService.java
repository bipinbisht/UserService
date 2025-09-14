package com.bipin.UserService.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;

@Service
public class JwtService {

	@Value("${security.jwt.secret}")
	private String secretKey;
	@Value("${security.jwt.expiration-time}")
	private long jwtExpirationMinutes;

	private Key key() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String subject) {
		Instant now = Instant.now();
		return Jwts.builder().setSubject(subject).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(jwtExpirationMinutes, ChronoUnit.MINUTES)))
				.signWith(key(), SignatureAlgorithm.HS256).compact();
	}

}
