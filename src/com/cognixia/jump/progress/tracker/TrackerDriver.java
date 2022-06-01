package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.TVShowDAO;
import com.cognixia.jump.dao.TVShow;


public class TrackerDriver {
	
	public static Scanner sc = new Scanner(System.in);
	private static User user;
	private static TVShowDAO showDAO;
	
	public static void main(String[] args) {
			
			try {
				System.out.println("\nEstablishing Connection...");
				user = new User();
				
				if (user.getExit()) {
					System.out.println("Exiting the application. Goodbye.");
					user.exit();
					return;
				}
				
				// TVShowDAO showDAO = new TVShowDAOClass(user);
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
						viewTVShows();
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
	
	public static void viewTVShows() {
		System.out.println("\nVIEW TV SHOWS:");
		System.out.println("=======================================");
		while (true) {
			System.out.println("\nChoose a view option of the TV Shows:\n");
			System.out.println(" 1) View all available TV Shows");
			System.out.println(" 2) View your shows that are not completed");
			System.out.println(" 3) View your shows that are in-progress");
			System.out.println(" 4) View your shows that you've completed");
			System.out.println(" 5) Exit to Main Menu\n");
			
			try {
				
				int option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
					case 1:
						viewAllShows();
						break;
					case 2:
						viewNotCompletedShows();
						break;
					case 3:
						viewInProgressShows();
						break;
					case 4:
						viewCompletedShows();
						break;
					case 5:
						// EXIT TO MAIN MENU
						System.out.println("Returning to Main Menu...\n");
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
			
			
			if(!repeatAction("view")) {
				return;
			}
		}
	}
	
	public static void viewAllShows() {
		List<TVShow> showList = showDAO.getAllTVShows();
		
		if (showList.isEmpty()) {
			System.out.println("There are no shows in the system.");
			return;
		}
		for (TVShow show : showList) {
			System.out.println(show);
		}
		System.out.println();
	}
	
	public static void viewCompletedShows() {
		showDAO.viewCompleted(null);
	}
	
	public static void viewInProgressShows() {
		showDAO.viewInProgress(null);
	}
	
	public static void viewNotCompletedShows() {
		showDAO.viewNotCompleted(null);
	}
	
	public static void addShowToTracker() {
		if(!repeatAction("add")) {
			//LOGIC FOR REPEAT
		}
	}
	
	public static void updateShowStatus() {
		
		if(!repeatAction("update")) {
			//LOGIC FOR REPEAT
		}
	}
	
	public static void removeShowFromTracker() {
		
		
		if(!repeatAction("remove")) {
			//LOGIC FOR REPEAT
		}
	}
	
	public static boolean repeatAction(String action) {
		while (true) {
			System.out.println("\nWould you like to " + action + " another show(s)? (Enter Option)");
			System.out.println("1. Yes");
			System.out.println("2. No");

			try {
				int repeat = sc.nextInt();
				sc.nextLine();

				switch (repeat) {
				case 1:
					break;
				case 2:
					System.out.println("Returning to main menu...\n");
					break;
				default:
					System.out.println("Not a valid input.");
					break;
				}
				if (repeat == 1) {
					return true;
				}

				if (repeat == 2) {
					return false;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nPlease enter 1 or 2");
			}
		}
	}
	
}

