package com.cognixia.jump.dao;

import java.util.List;

public interface TVShowDAO {

	public List<TVShow> getAllTVShows();
	public TVShow getTVShowById(int TVShowId);
	public TVShow getTVShowByName(String TVSTitle);
	
	public boolean addNotCompleted(TVShow TVshow);
	public boolean addInProgress(TVShow TVshow);
	public boolean addCompleted(TVShow TVshow);
	
	public boolean ViewNotCompleted(TVShow TVshow);
	public boolean ViewInProgress(TVShow TVshow);
	public boolean ViewCompleted(TVShow TVshow);
}
