package com.recruitment.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuritySettings extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private JwtBlacklist jwtBlacklist;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthExceptionHandler authExceptionHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authExceptionHandler)
		.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/auth", "/users").permitAll()
				.antMatchers("/v2/api-docs", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", 
						"/webjars/**").permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.addFilterBefore(new AuthenticationFilter(jwtUtil, objectMapper, jwtBlacklist, userDetailsService),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(HttpMethod.POST, "/auth", "/users")
			.antMatchers("/v2/api-docs", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", 
						"/webjars/**");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}