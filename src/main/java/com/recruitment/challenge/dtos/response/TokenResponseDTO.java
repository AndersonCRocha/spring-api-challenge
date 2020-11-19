package com.recruitment.challenge.dtos.response;

import java.util.Date;

public class TokenResponseDTO {

	private String token;
	private Date expiration;

	public TokenResponseDTO(String token, Date expiration) {
		this.token = token;
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

}