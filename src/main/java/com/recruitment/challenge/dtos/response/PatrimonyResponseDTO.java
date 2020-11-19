package com.recruitment.challenge.dtos.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.recruitment.challenge.entities.Brand;

public class PatrimonyResponseDTO {

	private String name;
	private String description;
	private Brand brand;
	private Long registerNumber;
	
	@NotNull
	@NotBlank(message = "name cannot be null or empty")
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Brand getBrand() {
		return brand;
	}
	
	public Long getRegisterNumber() {
		return registerNumber;
	}

	public PatrimonyResponseDTO setName(String name) {
		this.name = name;
		return this;
	}

	public PatrimonyResponseDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public PatrimonyResponseDTO setBrand(Brand brand) {
		this.brand = brand;
		return this;
	}
	
	public PatrimonyResponseDTO setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
		return this;
	}

}
