package com.recruitment.challenge.utilities.settings;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.recruitment.challenge.entities.Role;
import com.recruitment.challenge.repositories.RoleRepository;

@Configuration
public class RolesSettings {

	@Autowired
	private RoleRepository roleRepository;
	
	@Bean("rolesInit")
	public void init() {
		Arrays.asList(RoleEnum.values()).forEach(role -> {
			Optional<Role> roleOptional = roleRepository.findByName(role.name());
			if (!roleOptional.isPresent()) {
				roleRepository.save(new Role().setName(role.name()));
			}
		});
	}
	
	static enum RoleEnum {
		ROLE_USER, ROLE_ADMIN
	}
	
}