package com.recruitment.challenge.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponse {

	private int status;
	private String message;
	private Map<String, String> errors;

	public ExceptionResponse(int status, String message, Map<String, String> errors) {
		this(status, message);
		this.errors = errors;
	}

	public ExceptionResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public Map<String, String> addError(String name, String description) {
		if(Objects.isNull(this.errors)) {
			this.errors = new HashMap<String, String>();
		}
		errors.put(name, description);
		return this.errors;
	}
	
 	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}