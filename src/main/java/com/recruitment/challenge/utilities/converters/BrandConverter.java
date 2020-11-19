package com.recruitment.challenge.utilities.converters;

import com.recruitment.challenge.dtos.request.BrandRequestDTO;
import com.recruitment.challenge.dtos.response.BrandResponseDTO;
import com.recruitment.challenge.entities.Brand;

public class BrandConverter {

	public static Brand dtoToEntity(BrandRequestDTO brandRequestDTO) {
		return new Brand().setName(brandRequestDTO.getName());
	}

	public static BrandResponseDTO entityToDto(Brand brand) {
		return new BrandResponseDTO().setId(brand.getId()).setName(brand.getName());
	}

}