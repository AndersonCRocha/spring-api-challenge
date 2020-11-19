package com.recruitment.challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.challenge.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

}
