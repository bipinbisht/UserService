package com.bipin.UserService.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;

@Service
public class JwtService {

	@Value("${security.jwt.secret}")
	private String secretKey;
	@Value("${security.jwt.expiration-time}")
	private long jwtExpirationMinutes;

	// generating the token
	public String generateToken(String subject) {
		Instant now = Instant.now();
		return Jwts.builder().setSubject(subject).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(jwtExpirationMinutes, ChronoUnit.MINUTES)))
				.signWith(key(), SignatureAlgorithm.HS256).compact();
	}

	public String extractSubject(String token) {
		// extracting the subject from the token
		return parseAll(token).getBody().getSubject();
	}

	public boolean isValidToken(String token, UserDetails userDetails) {
		Claims claims = parseAll(token).getBody();
		// checking expiration and username
		return claims.getExpiration().after(new Date()) && userDetails.getUsername().equals(claims.getSubject());
	}

	private Jws<Claims> parseAll(String token) {
		// parsing the token
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
	}

	private Key key() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

}
