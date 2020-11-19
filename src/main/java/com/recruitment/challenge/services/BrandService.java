package com.recruitment.challenge.services;

import java.util.List;
import java.util.Optional;

import com.recruitment.challenge.dtos.request.BrandRequestDTO;
import com.recruitment.challenge.entities.Brand;
import com.recruitment.challenge.exceptions.EntityNotFoundException;

public interface BrandService {

	/**
	 * Receives an id and returns a Brand instance if exists
	 * 
	 * @param id
	 * @return Optional<Brand>
	 * @throws EntityNotFoundException
	 */
	Optional<Brand> findById(Long id);

	/**
	 * Receives an id and returns a Brand instance if exists, otherwise throw exception
	 * 
	 * @param id
	 * @return Brand
	 * @throws EntityNotFoundException
	 */
	Brand findByIdAndValidated(Long id) throws EntityNotFoundException;

	/**
	 * Return all brand registers of the database
	 * 
	 * @return List<Brand>
	 */
	List<Brand> findAll();

	/**
	 * Receives an brandDTO and save in the database
	 * 
	 * @param brandRequestDTO
	 * @return Brand
	 */
	Brand save(BrandRequestDTO brandRequestDTO);

	/**
	 * Receives id and brandDTO  and update in the database
	 * 
	 * @param brandRequestDTO
	 * @param id
	 * @return Brand
	 */
	Brand update(BrandRequestDTO brandRequestDTO, Long id);
	
	/**
	 * Receives an brand id and remove of the database if exists
	 * 
	 * @param id
	 * @throws EntityNotFoundException
	 */
	void deleteById(Long id) throws EntityNotFoundException;

}