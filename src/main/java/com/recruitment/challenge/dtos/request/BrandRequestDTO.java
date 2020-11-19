package com.recruitment.challenge.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BrandRequestDTO {

	private String name;
	
	@NotNull
	@NotBlank(message = "name cannot be null or empty")
	public String getName() {
		return name;
	}
	
	public BrandRequestDTO setName(String name) {
		this.name = name;
		return this;
	}

}
