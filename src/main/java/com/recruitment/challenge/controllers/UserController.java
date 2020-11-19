package com.recruitment.challenge.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recruitment.challenge.dtos.request.ChangePasswordRequestDTO;
import com.recruitment.challenge.dtos.request.UserRequestDTO;
import com.recruitment.challenge.dtos.response.UserResponseDTO;
import com.recruitment.challenge.entities.User;
import com.recruitment.challenge.exceptions.EntityNotFoundException;
import com.recruitment.challenge.services.UserService;
import com.recruitment.challenge.utilities.converters.UserConverter;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody UserRequestDTO userRequestDTO)
			throws EntityNotFoundException {
		User user = userService.save(userRequestDTO);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/users/{id}")
				.buildAndExpand(user.getId())
				.toUri())
				.build();
	}

	@PutMapping("change-password")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<UserResponseDTO> changePassword(HttpServletRequest request,
			@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
		User user = userService.changePassword(changePasswordRequestDTO, request.getHeader(HttpHeaders.AUTHORIZATION));

		return ResponseEntity.ok(UserConverter.entityToDTO(user));
	}
	
}
