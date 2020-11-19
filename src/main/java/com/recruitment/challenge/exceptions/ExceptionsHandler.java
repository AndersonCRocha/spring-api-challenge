package com.recruitment.challenge.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler({UsernameNotFoundException.class, EntityNotFoundException.class})
	public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({BrandNameException.class, ExistingEmailException.class})
	public ResponseEntity<ExceptionResponse> handleBrandNameException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handleUnauthorizedException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> handleAccessDeniedException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.FORBIDDEN.value(), 
				"You are not allowed to access this feature");
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = error instanceof FieldError ? ((FieldError) error).getField() 
					: error.getDefaultMessage().split(" ")[0];
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);

		});
		String message = "Validation failed for arguments!";
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), message, errors);
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}