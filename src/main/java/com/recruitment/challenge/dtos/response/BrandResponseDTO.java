package com.recruitment.challenge.dtos.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BrandResponseDTO {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}
	
	@NotNull
	@NotBlank(message = "name cannot be null or empty")
	public String getName() {
		return name;
	}

	public BrandResponseDTO setId(Long id) {
		this.id = id;
		return this;
	}
	
	public BrandResponseDTO setName(String name) {
		this.name = name;
		return this;
	}

}
