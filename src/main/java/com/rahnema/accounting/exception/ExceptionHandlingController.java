package com.rahnema.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<ExceptionResponse> invalidLoginHandler(InvalidException ex) {
		ExceptionResponse response = new ExceptionResponse("Otp Error", ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
	}
}
