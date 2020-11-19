package com.recruitment.challenge.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Object...params) {
		super(String.format(message, params));
	}

}