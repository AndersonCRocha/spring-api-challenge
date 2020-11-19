package com.recruitment.challenge.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthenticationRequestDTO {

	private String email;
	private String password;

	@NotNull
	@NotBlank(message = "email cannot be null or empty")
	public String getEmail() {
		return email;
	}

	@NotNull
	@NotBlank(message = "password cannot be null or empty")
	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}