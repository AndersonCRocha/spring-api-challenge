package com.recruitment.challenge.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recruitment.challenge.dtos.request.BrandRequestDTO;
import com.recruitment.challenge.dtos.response.BrandResponseDTO;
import com.recruitment.challenge.entities.Brand;
import com.recruitment.challenge.services.BrandService;
import com.recruitment.challenge.utilities.converters.BrandConverter;

@RestController
@RequestMapping("brands")
public class BrandController {

	private BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<BrandResponseDTO>> list() {
		return ResponseEntity.ok(brandService.findAll().stream().map(BrandConverter::entityToDto)
				.collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<BrandResponseDTO> get(@PathVariable Long id) {
		Brand brand = brandService.findByIdAndValidated(id);
		return ResponseEntity.ok(BrandConverter.entityToDto(brand));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Brand> create(@Valid @RequestBody BrandRequestDTO brandRequestDTO) {
		Brand brand = brandService.save(brandRequestDTO);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/brands/{id}")
				.buildAndExpand(brand.getId())
				.toUri())
				.build();
	}

	@PutMapping("{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<BrandResponseDTO> update(@Valid @RequestBody BrandRequestDTO brandRequestDTO, @PathVariable Long id) {
		Brand brand = brandService.update(brandRequestDTO, id);
		return ResponseEntity.ok(BrandConverter.entityToDto(brand));
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		brandService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}