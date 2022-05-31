package com.cognixia.jump.dao;

import java.time.LocalTime;

public class TVShow {

	public String id;
	public String title;
	public LocalTime length;
	public int rating;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalTime getLength() {
		return length;
	}
	public void setLength(LocalTime length) {
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
