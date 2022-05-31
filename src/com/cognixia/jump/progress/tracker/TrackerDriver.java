package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cognixia.jump.progress.tracker.ConnectionManager;

public class TrackerDriver {

	private static Scanner sc;
	
	public static void main(String[] args) {
			
			try {
				
				System.out.println("\nEstablishing Connection...");
				Connection conn = ConnectionManager.getConnection();
				System.out.println("\nConnection Success!\n");
				
				sc = new Scanner(System.in);
				
				userMenu(conn);
				
				sc.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}
	
	public static void userMenu(Connection conn) {
		System.out.println("Welcome to your TV Show Tracker!");
		System.out.println("Please choose an option below:\n");
		
		System.out.println(" 1) View TV Show Tracker");
//		System.out.println(" 2) View TV Shows not completed");
//		System.out.println(" 3) View TV Shows in progress");
//		System.out.println(" 4) View TV Show that are completed");
		System.out.println(" 2) Select TV Show to add to list");
		System.out.println(" 3) Update TV Show on list");
		System.out.println(" 4) Remove TV Show from list");
		System.out.println(" 5) Exit Application\n");
		
		
		
		
	}
}

