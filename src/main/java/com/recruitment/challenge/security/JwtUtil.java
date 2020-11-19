package com.recruitment.challenge.security;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	public static int JWT_EXPIRATION_DAYS = 7;
	public static String JWT_PROVIDER = "Bearer ";
	public static String JWT_ROLE_KEY = "role";
	public static String JWT_API_KEY = "secret-key-example";
	
	public String generateToken(String email, Set<String> roles) {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.DAY_OF_MONTH, JWT_EXPIRATION_DAYS);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expiration.getTime())
				.claim(JWT_ROLE_KEY, roles)
				.signWith(SignatureAlgorithm.HS512, JWT_API_KEY.getBytes())
				.compact();
	}

	public Boolean isValid(String token) {
		Boolean isValid = Boolean.FALSE;
		try {
			Date expiration = this.getExpirationFromToken(token);
			isValid = expiration != null ? !expiration.before(new Date()) : false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return isValid;
	}
	
	private Claims getClaimsFromToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(JWT_API_KEY.getBytes())
					.parseClaimsJws(token)
					.getBody();
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public String getEmailFromToken(String token) {
		return this.getClaimsFromToken(token).getSubject();
	}
	
	public Date getExpirationFromToken(String token) {
		return this.getClaimsFromToken(token).getExpiration();
	}
	
}