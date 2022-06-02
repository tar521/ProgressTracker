package com.cognixia.jump.dao;

import java.sql.Time;

public class TVShow {

	public int id;
	public String title;
	public String length;
	public float rating;
	
	
	public TVShow(int id, String title, String length, float rating) {
		super();
		this.id = id;
		this.title = title;
		this.length = length;
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Title: " + title + ", ID: " + id + ", Length: " + length + ", Rating: " + rating;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
