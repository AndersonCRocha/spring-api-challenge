package com.recruitment.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.challenge.entities.Patrimony;

public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> {

}