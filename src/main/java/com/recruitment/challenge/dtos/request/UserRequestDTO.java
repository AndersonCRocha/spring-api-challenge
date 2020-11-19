package com.recruitment.challenge.dtos.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.recruitment.challenge.utilities.validators.ValuesMatch;

@ValuesMatch(
	field = "password", 
	fieldMatch = "passwordConfirmation", 
	message = "password and passwordConfirmation doesn't match!"
)
public class UserRequestDTO {

	private String username;
	private String password;
	private String passwordConfirmation;
	private String email;
	private List<String> roles;

	@NotNull
	@NotBlank(message = "username cannot be null or empty")
	public String getUsername() {
		return username;
	}
	
	@NotNull
	@NotBlank(message = "password cannot be null or empty")
	public String getPassword() {
		return password;
	}
	
	@NotNull
	@NotBlank(message = "password confirmation cannot be null or empty")
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	@NotNull
	@Email(message = "invalid email")
	@NotBlank(message = "email cannot be null or empty")
	public String getEmail() {
		return email;
	}
	
	@NotNull(message = "roles cannot be null")
	public List<String> getRoles() {
		return roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}