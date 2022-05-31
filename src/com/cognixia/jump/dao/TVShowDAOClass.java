package com.cognixia.jump.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ConMgProperties;


public class TVShowDAOClass implements TVShowDAO {
	
	private Connection conn = ConMgProperties.getConnection();

	@Override
	public List<TVShow> getAllTVShows() {
		try{  
			
			
		} catch(SQLException e){ 
			
			
		}
		return null;
	}

	@Override
	public TVShow getTVShowById(int TVShowId) {
		try{   
			
			
		} catch(SQLException e){  
			
			
		}
		return null;
	}

	@Override
	public TVShow getTVShowByName(String TVSTitle) {
		try{   
			
			
		} catch(SQLException e){
			
			
		}
		return null;
	}

	@Override
	public boolean addNotCompleted(TVShow TVshow) {
		try{  
			
			
		} catch(SQLException e){ 
			
			
		}
		return false;
	}

	@Override
	public boolean addInProgress(TVShow TVshow) {
		try{  
			
			
		} catch(SQLException e){ 

		}
		return false;
	}

	@Override
	public boolean addCompleted(TVShow TVshow) {
		try{  
			
			
		} catch(SQLException e){
			
			
		}
		return false;
	}

	@Override
	public boolean ViewNotCompleted(TVShow TVshow) {
		try{ 
			
			
		} catch(SQLException e){  
			
			
		}
		return false;
	}

	@Override
	public boolean ViewInProgress(TVShow TVshow) {
		try{ 
			
			
		} catch(SQLException e){ 
			
			
		}
		return false;
	}

	@Override
	public boolean ViewCompleted(TVShow TVshow) {
		try{ 

		} catch(SQLException e){ 
			
			
		}
		return false;
	}

	
	
}
