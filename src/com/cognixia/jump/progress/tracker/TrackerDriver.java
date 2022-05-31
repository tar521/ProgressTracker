package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TrackerDriver {
	
	public static Scanner sc = new Scanner(System.in);
	private static User user;
	
	public static void main(String[] args) {
			
			try {
				System.out.println("\nEstablishing Connection...");
				user = new User();
				
				if (user.getExit()) {
					System.out.println("Exiting the application. Goodbye.");
					user.exit();
					return;
				}
				
				System.out.println("\nConnection Success!\n");
				
				userMenu();
				
				sc.close();
				user.exit();
				
			} catch (SQLException e) {
				System.out.println("Connection not established.");
				System.out.println("Exiting...");
			}
			
		
	}
	
	public static void userMenu() {
		System.out.println("Welcome to your TV Show Tracker!");
		
		while (true) {
			System.out.println("Please choose an option below:\n");
			
			System.out.println(" 1) View TV Show Tracker");
	//		System.out.println(" 2) View TV Shows not completed");
	//		System.out.println(" 3) View TV Shows in progress");
	//		System.out.println(" 4) View TV Show that are completed");
			System.out.println(" 2) Select TV Show to add to list");
			System.out.println(" 3) Update TV Show on list");
			System.out.println(" 4) Remove TV Show from list");
			System.out.println(" 5) Exit Application\n");
			try {
				
				int option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
					case 1:
						// VIEW USER TRACKER
						System.out.println("UNIMPLEMENTED OPTION");
						break;
					case 2:
						// ADD SHOW TO TRACKER
						System.out.println("UNIMPLEMENTED OPTION");
						break;
					case 3:
						// UPDATE SHOW ON TRACKER - STATUS
						System.out.println("UNIMPLEMENTED OPTION");
						break;
					case 4:
						// REMOVE SHOW FROM TRACKER
						System.out.println("UNIMPLEMENTED OPTION");
						break;
					case 5:
						// EXIT TRACKER APP
						System.out.println("Thanks for using the TV Show Tracker!");
						break;
					default:
						System.out.println("Invalid Input - Please enter a listed option");
				}
			
				if (option == 5) {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please input an integer option");
				sc.nextLine();
			}
		}
	}
}

