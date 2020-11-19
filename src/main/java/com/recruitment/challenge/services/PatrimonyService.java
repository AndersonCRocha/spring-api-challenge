package com.recruitment.challenge.services;

import java.util.List;
import java.util.Optional;

import com.recruitment.challenge.dtos.request.PatrimonyRequestDTO;
import com.recruitment.challenge.entities.Patrimony;
import com.recruitment.challenge.exceptions.EntityNotFoundException;

public interface PatrimonyService {

	/**
	 * Receives an id and returns a Patrimony instance if exists
	 * 
	 * @param id
	 * @return Optional<Patrimony>
	 */
	Optional<Patrimony> findById(Long id);
	
	/**
	 * Receives an id and returns a Patrimony instance if exists, otherwise throw exception
	 * 
	 * @param id
	 * @return Patrimony
	 * @throws EntityNotFoundException
	 */
	Patrimony findByIdAndValidated(Long id) throws EntityNotFoundException;

	/**
	 * Return all patrimony registers of the database
	 * 
	 * @return List<Patrimony>
	 */
	List<Patrimony> findAll();

	/**
	 * Receives a patrimonyRequestDTO and save in the database
	 * 
	 * @param patrimonyRequestDTO
	 * @return Patrimony
	 */
	Patrimony save(PatrimonyRequestDTO patrimonyRequestDTO);

	/**
	 * Receives id and patrimonyRequestDTO  and update in the database
	 * 
	 * @param patrimonyRequestDTO
	 * @param id
	 * @return Patrimony
	 */
	Patrimony update(PatrimonyRequestDTO patrimonyRequestDTO, Long id);

	/**
	 * Receives a patrimony id and remove of the database if exists
	 * 
	 * @param id
	 * @throws EntityNotFoundException
	 */
	void deleteById(Long id) throws EntityNotFoundException;

}