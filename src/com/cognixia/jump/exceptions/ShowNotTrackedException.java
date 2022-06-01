package com.cognixia.jump.exceptions;

public class ShowNotTrackedException extends Exception{


	private static final long serialVersionUID = 7926638075360083316L;

	public ShowNotTrackedException(int id) {
		super("TV Show with id " + id + " has not been tracked by this user.");
	}
}
