package com.cognixia.jump.dao;

import java.util.List;

public interface TVShowDAO {

	public void printAllTVShows();
	public List<TVShow> getAllUserShows();
	
	public TVShow getTVShowById(int TVShowId);
	public TVShow getTVShowByName(String TVSTitle);
	
	public boolean  addShow (TVShow TVshow);
	public boolean  removeShow (TVShow TVshow);
	 
	public boolean addNotCompleted(TVShow TVshow);
	public boolean addInProgress(TVShow TVshow);
	public boolean addCompleted(TVShow TVshow);
	
	public int ViewNotCompleted();
	public int ViewInProgress();
	public int ViewCompleted();
}
