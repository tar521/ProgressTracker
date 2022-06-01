package com.cognixia.jump.dao;

import java.sql.Time;

public class TVShow {

	public int id;
	public String title;
	public Time length;
	public float rating;
	
	
	public TVShow(int id, String title, Time length, float rating) {
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
	public Time getLength() {
		return length;
	}
	public void setLength(Time length) {
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
		return "TVShow [id=" + id + ", title=" + title + ", length=" + length + ", rating=" + rating + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
