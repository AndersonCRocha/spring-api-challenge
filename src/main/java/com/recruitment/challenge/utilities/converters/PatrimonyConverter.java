package com.recruitment.challenge.utilities.converters;

import com.recruitment.challenge.dtos.request.PatrimonyRequestDTO;
import com.recruitment.challenge.dtos.response.PatrimonyResponseDTO;
import com.recruitment.challenge.entities.Patrimony;

public class PatrimonyConverter {

	public static Patrimony dtoToEntity(PatrimonyRequestDTO patrimonyRequestDTO) {
		return new Patrimony()
				.setName(patrimonyRequestDTO.getName())
				.setDescription(patrimonyRequestDTO.getDescription());
	}

	public static PatrimonyResponseDTO entityToDto(Patrimony patrimony) {
		return new PatrimonyResponseDTO()
				.setName(patrimony.getName())
				.setDescription(patrimony.getDescription())
				.setBrand(patrimony.getBrand())
				.setRegisterNumber(patrimony.getRegisterNumber());
	}

}