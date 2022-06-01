package com.cognixia.jump.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.progress.tracker.User;


public class TVShowDAOClass implements TVShowDAO {
	private User user;
	
	
	public TVShowDAOClass(User user) {
		super();
		this.user = user;
	}

	private Connection conn = user.getConn();
	
	@Override
	public List<TVShow> getAllTVShows() {
		try{  
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM shows WHERE TVShow_id IN { SELECT TVShow_id FROM user_shows WHERE  User_ID = ?}");
			pstmt.setInt(1,user.getId());
			
			ResultSet rs = pstmt.executeQuery();
			List<TVShow> tvSList = new ArrayList<TVShow>();
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				tvSList.add(tvS);
			}
			return tvSList;
			
		} catch(SQLException e){ 
			
			System.out.println("Could not get the TV Shows List");
		}
		return null;
	}

	@Override
	public TVShow getTVShowById(int TVShowId) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM shows WHERE TVShow_id = ?");
			pstmt.setInt(1,user.getId());
			pstmt.setInt(2,TVShowId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				return tvS;
			}
			

		} catch(SQLException e){  
			System.out.println("Could not Locate the TV Shows with ID ==> " + TVShowId);
			
		}
		return null;
	}

	@Override
	public TVShow getTVShowByName(String TVSTitle) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM shows WHERE  Title = ?");
			pstmt.setInt(1,user.getId());
			pstmt.setString(2,TVSTitle);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				return tvS;
			}
			
		} catch(SQLException e){
			System.out.println("Could not Locate the TV Shows with Title==> " + TVSTitle);
			
		}
		return null;
	}

	@Override
	public boolean addShow(TVShow show) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user_shows(user_id, show_id, status)"
					+ " VALUES (?, ?, 'NC'");
			pstmt.setInt(1,user.getId());
			pstmt.setInt(2,show.getId());
			
			int result = pstmt.executeUpdate();
			if ( result > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("\"Failed to add Tv Show:" + show.getId() + " to your tracker");
		}
		
		return false;
	}
	
	@Override
	public boolean addNotCompleted(TVShow TVshow) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user_shows SET Status = NC WHERE User_ID = ? AND TVShow_id = ?");
			pstmt.setInt(1,user.getId());
			pstmt.setInt(2,TVshow.getId());
			
			int result = pstmt.executeUpdate();
			if ( result > 0) {
				return true;
			}
		} catch(SQLException e){ 
			System.out.println("Failed to add Tv Show:" + TVshow.getId() + " to Not Complete TV Show List for User:" + user.getId());
			
		}
		return false;
	}

	@Override
	public boolean addInProgress(TVShow TVshow){
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user_shows SET Status = IP WHERE User_ID = ? AND TVShow_id = ?");
			pstmt.setInt(1,user.getId());
			pstmt.setInt(2,TVshow.getId());
			
			int result = pstmt.executeUpdate();
			if ( result > 0) {
				return true;
			}
		} catch(SQLException e){ 
			System.out.println("Failed to add Tv Show:" + TVshow.getId() + " to In-Progress TV Show List for User:" + user.getId());
			
		}
		return false;
	}

	@Override
	public boolean addCompleted(TVShow TVshow){
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user_shows SET Status = C WHERE User_ID = ? AND TVShow_id = ?");
			pstmt.setInt(1,user.getId());
			pstmt.setInt(2,TVshow.getId());
			
			int result = pstmt.executeUpdate();
			if ( result > 0) {
				return true;
			}
		} catch(SQLException e){ 
			System.out.println("Failed to add Tv Show:" + TVshow.getId() + " to Complete TV Show List for User:" + user.getId());
			
		}
		return false;
	}

	@Override
	public int ViewNotCompleted(TVShow TVshow) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user_shows WHERE User_ID = ? AND Status = NC");
			pstmt.setInt(1,user.getId());
			
			
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				System.out.println(tvS.toString());
				count++;
			}
			return count;

		} catch(SQLException e){  
			System.out.println("Failed to view Not Complete TV Show List of User:" + user.getId());
			
		}
		return -1;	
	}

	@Override
	public int ViewInProgress(TVShow TVshow) {
		try{ 
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user_shows WHERE User_ID = ? AND Status = IP");
			pstmt.setInt(1,user.getId());
			
			
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				System.out.println(tvS.toString());
				count++;
			}
			return count;

		} catch(SQLException e){  
			System.out.println("Failed to view In-Progress TV Show List of User:" + user.getId());
			
		}
		return -1;
	}

	@Override
	public int ViewCompleted(TVShow TVshow) {
		try{ 
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user_shows WHERE User_ID = ? AND Status = C");
			pstmt.setInt(1,user.getId());
			
			
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				int id = rs.getInt("TVShow_id");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				int rating = rs.getInt("rating");
				TVShow tvS = new TVShow(id, title, length, rating);
				System.out.println(tvS.toString());
				count++;
			}
			return count;

		} catch(SQLException e){  
			System.out.println("Failed to view Complete TV Show List of User:" + user.getId());
			
		}
		return -1;
	}

	
	
}
