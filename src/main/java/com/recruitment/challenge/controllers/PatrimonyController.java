package com.recruitment.challenge.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recruitment.challenge.dtos.request.PatrimonyRequestDTO;
import com.recruitment.challenge.dtos.response.PatrimonyResponseDTO;
import com.recruitment.challenge.entities.Patrimony;
import com.recruitment.challenge.services.PatrimonyService;
import com.recruitment.challenge.utilities.converters.PatrimonyConverter;

@RestController
@RequestMapping("patrimonies")
public class PatrimonyController {

	private PatrimonyService patrimonyService;
	
	public PatrimonyController(PatrimonyService patrimonyService) {
		this.patrimonyService = patrimonyService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<PatrimonyResponseDTO>> list() {
		return ResponseEntity.ok(patrimonyService.findAll().stream().map(PatrimonyConverter::entityToDto)
				.collect(Collectors.toList()));
	}

	@GetMapping("{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<PatrimonyResponseDTO> get(@PathVariable Long id) {
		Patrimony patrimony = patrimonyService.findByIdAndValidated(id);
		return ResponseEntity.ok(PatrimonyConverter.entityToDto(patrimony));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Patrimony> create(@Valid @RequestBody PatrimonyRequestDTO patrimonyRequestDTO) {
		Patrimony patrimony = patrimonyService.save(patrimonyRequestDTO);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/patrimonies/{id}")
				.buildAndExpand(patrimony.getId())
				.toUri())
				.build();
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<PatrimonyResponseDTO> update(@Valid @RequestBody PatrimonyRequestDTO patrimonyRequestDTO, 
			@PathVariable Long id) {
		Patrimony patrimony = patrimonyService.update(patrimonyRequestDTO, id);
		return ResponseEntity.ok(PatrimonyConverter.entityToDto(patrimony));
	}
	
}