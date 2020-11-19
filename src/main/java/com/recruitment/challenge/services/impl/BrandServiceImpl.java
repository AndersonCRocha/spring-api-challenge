package com.recruitment.challenge.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recruitment.challenge.dtos.request.BrandRequestDTO;
import com.recruitment.challenge.entities.Brand;
import com.recruitment.challenge.exceptions.BrandNameException;
import com.recruitment.challenge.exceptions.EntityNotFoundException;
import com.recruitment.challenge.repositories.BrandRepository;
import com.recruitment.challenge.services.BrandService;
import com.recruitment.challenge.utilities.converters.BrandConverter;

@Service
public class BrandServiceImpl implements BrandService {

	private BrandRepository brandRepository;
	
	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}
	
	@Override
	public Optional<Brand> findById(Long id) {
		return brandRepository.findById(id);
	}

	@Override
	public Brand findByIdAndValidated(Long id) throws EntityNotFoundException {
		return brandRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Brand not found for id: %d", id));
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Brand save(BrandRequestDTO brandRequestDTO) {
		this.validateBrandRequestDTO(brandRequestDTO);
		return brandRepository.save(BrandConverter.dtoToEntity(brandRequestDTO));
	}

	@Override
	public Brand update(BrandRequestDTO brandRequestDTO, Long id) {
		Brand brand = this.findByIdAndValidated(id);
		this.validateBrandRequestDTO(brandRequestDTO);
		brand = BrandConverter.dtoToEntity(brandRequestDTO).setId(id);
		
		return brandRepository.save(brand);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		Brand brand = this.findByIdAndValidated(id);
		brandRepository.delete(brand);
	}

	private void validateBrandRequestDTO(BrandRequestDTO brandRequestDTO) {
		String name = brandRequestDTO.getName();
		if (brandRepository.existsByName(name)) {
			throw new BrandNameException(String.format("There is already a registered brand with that name: %s", name));
		}
	}

}