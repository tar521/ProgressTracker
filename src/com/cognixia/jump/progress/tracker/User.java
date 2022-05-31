package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {

	private String username;
	private Connection conn;
	private int id;
	private boolean exit;
	
	public User() throws SQLException {
		exit = false;
		
		conn = ConMgProperties.getConnection();
		login();
		
	}
	
	private void login() throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("USER LOGIN");
		System.out.println("=========================\n");
		
		while (true) {
			System.out.println("(To exit type \"exit\" at any input)");
			System.out.println("Please input username:");
			
			// getting username
			username = sc.nextLine();
			if (username.equalsIgnoreCase("exit")) {
				exit = true;
				return;
			}
			
			// getting password
			System.out.println("Please input password:");
			String password = sc.nextLine();
			if (password.equalsIgnoreCase("exit")) {
				exit = true;
				return;
			}
			
			// Executing query to check for existing user
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery("select * from users where username ="+username+" and password ="+password);
			
			// if empty - reenter information
			if (!rs.next()) {
				System.out.println("Incorrect username or password -- Please try again\n");
				username = "";
				continue;
			}
			
			// Retrieve user_id for use later
			System.out.println("Login successful!");
			System.out.println("=================\n");
			rs.first();
			id = rs.getInt("id");
						
			rs.close();
			stmt.close();
			break;
		}
		sc.close();
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean getExit() {
		return exit;
	}
	
	public void exit() throws SQLException {
		conn.close();
	}
	
}
