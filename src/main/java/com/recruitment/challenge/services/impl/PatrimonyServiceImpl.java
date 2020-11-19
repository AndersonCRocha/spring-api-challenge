package com.recruitment.challenge.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recruitment.challenge.dtos.request.PatrimonyRequestDTO;
import com.recruitment.challenge.entities.Brand;
import com.recruitment.challenge.entities.Patrimony;
import com.recruitment.challenge.exceptions.EntityNotFoundException;
import com.recruitment.challenge.repositories.PatrimonyRepository;
import com.recruitment.challenge.services.BrandService;
import com.recruitment.challenge.services.PatrimonyService;
import com.recruitment.challenge.utilities.converters.PatrimonyConverter;

@Service
public class PatrimonyServiceImpl implements PatrimonyService {

	private PatrimonyRepository patrimonyRepository;
	private BrandService brandService;
	
	public PatrimonyServiceImpl(PatrimonyRepository patrimonyRepository, BrandService brandService) {
		this.patrimonyRepository = patrimonyRepository;
		this.brandService = brandService;
	}
	
	@Override
	public Optional<Patrimony> findById(Long id) {
		return patrimonyRepository.findById(id);
	}

	@Override
	public Patrimony findByIdAndValidated(Long id) throws EntityNotFoundException {
		return this.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Patrimony not found for id: %d", id));
	}

	@Override
	public List<Patrimony> findAll() {
		return patrimonyRepository.findAll();
	}

	@Override
	public Patrimony save(PatrimonyRequestDTO patrimonyRequestDTO) {
		Patrimony patrimony = PatrimonyConverter.dtoToEntity(patrimonyRequestDTO);
		Long brandId = patrimonyRequestDTO.getBrandId();
		Brand brand = brandService.findByIdAndValidated(brandId);
		patrimony.setBrand(brand);
		
		return patrimonyRepository.save(patrimony);
	}

	@Override
	public Patrimony update(PatrimonyRequestDTO patrimonyRequestDTO, Long id) {
		Patrimony patrimony = this.findByIdAndValidated(id);
		Long brandId = patrimonyRequestDTO.getBrandId();
		Brand brand = brandService.findByIdAndValidated(brandId);

		patrimony = PatrimonyConverter.dtoToEntity(patrimonyRequestDTO).setId(id).setBrand(brand)
				.setRegisterNumber(patrimony.getRegisterNumber());
		
		return patrimonyRepository.save(patrimony);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		Patrimony patrimony = this.findByIdAndValidated(id);
		patrimonyRepository.delete(patrimony);
	}

}