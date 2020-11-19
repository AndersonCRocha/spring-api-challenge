package com.recruitment.challenge.services;

import java.util.List;
import java.util.Optional;

import com.recruitment.challenge.dtos.request.ChangePasswordRequestDTO;
import com.recruitment.challenge.dtos.request.UserRequestDTO;
import com.recruitment.challenge.entities.User;
import com.recruitment.challenge.exceptions.EntityNotFoundException;

public interface UserService {
	
	/**
	 * Receives an id and returns a User instance if exists
	 * 
	 * @param id
	 * @return Optional<User>
	 * @throws EntityNotFoundException
	 */
	Optional<User> findById(Long id);

	/**
	 * Receives an id and returns a User instance if exists, otherwise throw exception
	 * 
	 * @param id
	 * @return User
	 * @throws EntityNotFoundException
	 */
	User findByIdAndValidated(Long id) throws EntityNotFoundException;

	/**
	 * Return all user registers of the database
	 * 
	 * @return List<User>
	 */
	List<User> findAll();

	/**
	 * Receives an userRegisterDTO and save in the database
	 * 
	 * @param userRequestDTO
	 * @return User
	 * @throws EntityNotFoundException 
	 */
	User save(UserRequestDTO userRequestDTO) throws EntityNotFoundException;

	/**
	 * Receives an user id and remove of the database if exists
	 * 
	 * @param id
	 * @throws EntityNotFoundException
	 */
	void deleteById(Long id) throws EntityNotFoundException;

	/**
	 * Receives a ChangePasswordRequestDTO and change the password of the logged in user
	 * 
	 * @param changePasswordRequestDTO
	 * @param token
	 * @return User
	 */
	User changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String token);
	
}