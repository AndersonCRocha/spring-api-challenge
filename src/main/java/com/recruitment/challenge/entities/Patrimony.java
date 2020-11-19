package com.recruitment.challenge.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomUtils;

@Entity
@Table(name = "patrimonies")
@SequenceGenerator(name = "sq_patrimonies", sequenceName = "sq_patrimonies", allocationSize = 1)
public class Patrimony {

	private Long id;
	private String name;
	private String description;
	private Brand brand;
	private Long registerNumber;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_patrimonies")
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Brand getBrand() {
		return brand;
	}
	
	public Long getRegisterNumber() {
		return registerNumber;
	}

	public Patrimony setId(Long id) {
		this.id = id;
		return this;
	}

	public Patrimony setName(String name) {
		this.name = name;
		return this;
	}

	public Patrimony setDescription(String description) {
		this.description = description;
		return this;
	}

	public Patrimony setBrand(Brand brand) {
		this.brand = brand;
		return this;
	}
	
	public Patrimony setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
		return this;
	}

	@PrePersist
	public void prePersist() {
		this.registerNumber = RandomUtils.nextLong(10000000, 99999999);
	}
	
}