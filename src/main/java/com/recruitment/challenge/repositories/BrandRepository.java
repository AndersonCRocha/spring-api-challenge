package com.recruitment.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.challenge.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Boolean existsByName(String name);
	
}
