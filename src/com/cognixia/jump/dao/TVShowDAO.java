package com.cognixia.jump.dao;

import java.util.List;

public interface TVShowDAO {

	public List<TVShow> getAllTVShows();
	public TVShow getTVShowById(int TVShowId);
	public TVShow getTVShowByName(String TVSTitle);
	
	public boolean addNotCompleted(TVShow TVshow);
	public boolean addInProgress(TVShow TVshow);
	public boolean addCompleted(TVShow TVshow);
	
	public int ViewNotCompleted(TVShow TVshow);
	public int ViewInProgress(TVShow TVshow);
	public int ViewCompleted(TVShow TVshow);
}
