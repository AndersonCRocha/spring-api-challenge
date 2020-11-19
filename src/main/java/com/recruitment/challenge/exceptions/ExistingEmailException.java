package com.recruitment.challenge.exceptions;

public class ExistingEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingEmailException(String message) {
		super(message);
	}
	
}
