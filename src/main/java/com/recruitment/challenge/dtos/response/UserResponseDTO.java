package com.recruitment.challenge.dtos.response;

import java.util.Set;

public class UserResponseDTO {

	private Long id;
	private String username;
	private String email;
	private Set<String> roles;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public UserResponseDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public UserResponseDTO setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserResponseDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserResponseDTO setRoles(Set<String> roles) {
		this.roles = roles;
		return this;
	}

}