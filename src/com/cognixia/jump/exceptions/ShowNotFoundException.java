package com.cognixia.jump.exceptions;

public class ShowNotFoundException extends Exception{

	private static final long serialVersionUID = -6900648264905677981L;
	
	public ShowNotFoundException(int id) {
		super("TV show with the ID " + id + "not found.");
	}

}
