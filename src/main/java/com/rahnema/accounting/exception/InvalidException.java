package com.rahnema.accounting.exception;

public class InvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865946771107452442L;
	
	public static final String DEFAULT_MESSAGE = "Otp Failed";

	public InvalidException() {
		super(DEFAULT_MESSAGE);
	}

	public InvalidException(String message) {
		super(message);
	}

}
