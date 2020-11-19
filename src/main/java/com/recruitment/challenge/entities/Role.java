package com.recruitment.challenge.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@SequenceGenerator(name = "sq_roles", sequenceName = "sq_roles", allocationSize = 1)
public class Role {

	private Long id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_roles")
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Role setId(Long id) {
		this.id = id;
		return this;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

}