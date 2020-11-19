package com.recruitment.challenge.utilities.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.challenge.security.JwtUtil;

@Configuration
public class BeansSettings {

	@Bean
	public JwtUtil getJwtUtil() {
		return new JwtUtil();
	}
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
	}
	
}
