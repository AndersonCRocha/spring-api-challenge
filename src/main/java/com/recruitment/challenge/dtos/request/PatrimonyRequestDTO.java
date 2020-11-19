package com.recruitment.challenge.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatrimonyRequestDTO {

	private String name;
	private String description;
	private Long brandId;
	
	@NotNull
	@NotBlank(message = "name cannot be null or empty")
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@NotNull(message = "brand id cannot be null")
	public Long getBrandId() {
		return brandId;
	}

	public PatrimonyRequestDTO setName(String name) {
		this.name = name;
		return this;
	}

	public PatrimonyRequestDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public PatrimonyRequestDTO setBrandId(Long brandId) {
		this.brandId = brandId;
		return this;
	}

}
