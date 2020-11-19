package com.recruitment.challenge.utilities.converters;

import com.recruitment.challenge.dtos.request.UserRequestDTO;
import com.recruitment.challenge.dtos.response.UserResponseDTO;
import com.recruitment.challenge.entities.User;

public class UserConverter {

	public static User dtoToEntity(UserRequestDTO userRegisterDTO) {
		return new User()
				.setUsername(userRegisterDTO.getUsername())
				.setEmail(userRegisterDTO.getEmail());
	}
	
	public static UserResponseDTO entityToDTO(User user) {
		return new UserResponseDTO()
				.setId(user.getId())
				.setUsername(user.getUsername())
				.setEmail(user.getEmail())
				.setRoles(user.getRoleNames());
	}
	
}