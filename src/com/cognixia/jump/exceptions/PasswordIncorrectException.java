package com.cognixia.jump.exceptions;

public class PasswordIncorrectException extends Exception {

	private static final long serialVersionUID = 5191754170112673200L;
	
	public PasswordIncorrectException(String password) {
		super("Password Incorrect. Please try again.");
	}

}
