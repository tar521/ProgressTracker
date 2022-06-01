package com.cognixia.jump.exceptions;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = -4234783727749917944L;
	
	public UserNotFoundException(String username) {
		super("Username not found. Please try again.");
	}

}
