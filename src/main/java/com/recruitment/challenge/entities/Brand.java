package com.recruitment.challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
@SequenceGenerator(name = "sq_brands", sequenceName = "sq_brands", allocationSize = 1)
public class Brand {

	private Long id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_brands")
	public Long getId() {
		return id;
	}

	@Column(unique = true)
	public String getName() {
		return name;
	}

	public Brand setId(Long id) {
		this.id = id;
		return this;
	}

	public Brand setName(String name) {
		this.name = name;
		return this;
	}

}