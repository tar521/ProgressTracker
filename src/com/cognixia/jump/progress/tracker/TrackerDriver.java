package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.progress.tracker.ConnectionManager;

public class TrackerDriver {

	public static void main(String[] args) {
			
			try {
				
				Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				
				
				
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}
}

