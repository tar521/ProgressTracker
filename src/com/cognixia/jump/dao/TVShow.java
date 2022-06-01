package com.cognixia.jump.dao;

import java.sql.Time;

public class TVShow {

	public int id;
	public String title;
	public Time length;
	public int rating;
	
	
	public TVShow(int id, String title, Time length, int rating) {
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "TVShow [id=" + id + ", title=" + title + ", length=" + length + ", rating=" + rating + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
