package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// this is the single connection we will have and we create
	private static Connection connection = null;

	//TODO : CHANGE URL TO WORKING DATABASE
	private static final String URL = "jdbc:mysql://localhost:3306/university";
	// ?serverTimezone=EST5EDT <-- add to end of URL if on Mac or Linux
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; // change password if need be,
												   // might be Root@123 for mac users
	
	
	// takes care of making connection based on our URL, USERNAME, and PASSSWORD
	// we keep it as private so that we can have getConnection() manage when the connection
	// should be made
	private static void makeConnection() {

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {
			System.out.println("Could not connect to database");
		}

	}

	// checks if connection is null, if it is, connection has not been made and will
	// call makeConnection() to make connection, then return that connection
	public static Connection getConnection() {

		if (connection == null) {
			makeConnection();
		}

		return connection;
	}


}