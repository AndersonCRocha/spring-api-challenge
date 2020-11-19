package com.recruitment.challenge.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.challenge.exceptions.ExceptionResponse;

public class AuthenticationFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private ObjectMapper objectMapper;
	private JwtBlacklist jwtBlacklist; 
	private UserDetailsService userDetailsService;
	
	public AuthenticationFilter(JwtUtil jwtUtil, ObjectMapper objectMapper, JwtBlacklist jwtBlacklist,
			UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.objectMapper = objectMapper;
		this.jwtBlacklist = jwtBlacklist;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		jwtBlacklist.cleanBlacklist();
		
		Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));
		String token = tokenOptional.orElse("").replace(JwtUtil.JWT_PROVIDER, "");
		
		if (StringUtils.isNotBlank(token) && jwtUtil.isValid(token) && !jwtBlacklist.contains(token)) {
			String email = jwtUtil.getEmailFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			PrintWriter writer = response.getWriter();
			ExceptionResponse error = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token!");
			String errorString = objectMapper.writeValueAsString(error);
			
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			writer.write(errorString);
			
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}

}
