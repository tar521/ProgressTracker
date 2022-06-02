package com.cognixia.jump.exceptions;

public class ShowAlreadyTrackedException extends Exception{

	private static final long serialVersionUID = 3897332064376805997L;
	
	public ShowAlreadyTrackedException(int id) {
		super("TV Show with ID: " + id + " is already being tracked.");
	}

}
