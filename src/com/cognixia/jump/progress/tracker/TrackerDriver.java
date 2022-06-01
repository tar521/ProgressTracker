package com.cognixia.jump.progress.tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.TVShowDAO;
import com.cognixia.jump.dao.TVShowDAOClass;
import com.cognixia.jump.exceptions.ShowNotFoundException;
import com.cognixia.jump.exceptions.ShowNotTrackedException;
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

			showDAO = new TVShowDAOClass(user);
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
					//System.out.println("UNIMPLEMENTED OPTION");
					break;
				case 2:
					// ADD SHOW TO TRACKER
					addShowToTracker();
					break;
				case 3:
					// UPDATE SHOW ON TRACKER - STATUS
					updateShowStatus();
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

			if (!repeatAction("view")) {
				return;
			}
		}
	}

	public static void viewAllShows() {
		List<TVShow> showList = showDAO.getAllUserShows();

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
		int count = showDAO.ViewCompleted(null);

		if (count == 0) {
			System.out.println("No shows completed.");
		} else if (count == 1) {
			System.out.println(count + " show in progress.");
		} else {
			System.out.println(count + " show(s) have been completed.");
		}
	}

	public static void viewInProgressShows() {
		int count = showDAO.ViewInProgress(null);

		if (count == 0) {
			System.out.println("No shows in progress.");
		} else if (count == 1) {
			System.out.println(count + " show in progress");
		} else {
			System.out.println(count + " shows in progress.");
		}
	}

	public static void viewNotCompletedShows() {
		int count = showDAO.ViewNotCompleted(null);

		if (count == 0) {
			System.out.println("No shows in queue.");
		} else if (count == 1) {
			System.out.println(count + " show in queue.");
		} else {
			System.out.println(count + " shows in queue");
		}
	}

	public static void addShowToTracker() {

		System.out.println("ADD SHOW TO TRACKER:");
		System.out.println("======================\n");

		while (true) {
			System.out.println("\nChoose an id from the available TV Shows:\n");

			showDAO.printAllTVShows();

			try {

				int option = sc.nextInt();
				sc.nextLine();

				TVShow temp = showDAO.getTVShowById(option);

				if (temp == null) {
					System.out.println("Show with id = " + option + " was not found.");
					throw new ShowNotFoundException(option);
				}

				System.out.println("Adding \"" + temp.getTitle() + "\" to your tracker.");
				if (!showDAO.addShow(temp)) {
					System.out.println("Show Not Added");
					throw new ShowNotTrackedException(option);
				}

				System.out.println("Show successfully added!");

			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please input an integer option");
				sc.nextLine();
			} catch (ShowNotFoundException e) {
				System.out.println("Please try again.\n");
			} catch (ShowNotTrackedException e) {
				System.out.println("Error Occured :: Returning to main menu.");
				return;
			}

			if (!repeatAction("add")) {
				System.out.println("Returning to main menu...");
				return;
			}
		}
	}

	public static void updateShowStatus() {

		System.out.println("UPDATE SHOW IN TRACKER");
		System.out.println("======================\n");

		while (true) {
			System.out.println("\nChoose an id from your shows to update:\n");

			System.out.println("Not completed shows:");
			showDAO.ViewNotCompleted(null);
			System.out.println("In-Progress shows:");
			showDAO.ViewInProgress(null);
			System.out.println("Completed shows:");
			showDAO.ViewCompleted(null);

			try {

				int id = sc.nextInt();
				sc.nextLine();

				TVShow temp = showDAO.getTVShowById(id);

				if (temp == null) {
					System.out.println("Show with id = " + id + " was not found.");
					throw new ShowNotFoundException(id);
				}

				System.out.println("Choose a status to update \"" + temp.getTitle() + "\" to:");
				System.out.println(" 1) NC = Not Completed");
				System.out.println(" 2) IP = In-Progress");
				System.out.println(" 3) C = Completed");

				int option = sc.nextInt();
				sc.nextLine();

				boolean success = false;

				switch (option) {
				case 1:
					System.out.println("Updating \"" + temp.getTitle() + "\" on your tracker.");
					success = showDAO.addNotCompleted(temp);
					break;
				case 2:
					System.out.println("Updating \"" + temp.getTitle() + "\" on your tracker.");
					success = showDAO.addInProgress(temp);
					break;
				case 3:
					System.out.println("Updating \"" + temp.getTitle() + "\" on your tracker.");
					success = showDAO.addCompleted(temp);
					break;
				default:
					System.out.println("Not a valid input.");
					break;
				}
				if (!success) {
					System.out.println("Show Not Updated");
					throw new ShowNotTrackedException(id);
				}

				System.out.println("Show successfully updated!");

			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please input an integer option");
				sc.nextLine();
			} catch (ShowNotFoundException e) {
				System.out.println("Please try again.\n");
			} catch (ShowNotTrackedException e) {
				System.out.println("Error Occured :: Returning to main menu.");
				return;
			}

			if (!repeatAction("update")) {
				// LOGIC FOR REPEAT
			}
		}
	}

	public static void removeShowFromTracker() {

		System.out.println("REMOVE SHOW FROM TRACKER:");
		System.out.println("======================\n");

		while (true) {
			System.out.println("\nChoose an id from the available TV Shows:\n");

			viewAllShows();

			try {

				int option = sc.nextInt();
				sc.nextLine();

				TVShow temp = showDAO.getTVShowById(option);

				if (temp == null) {
					System.out.println("Show with id = " + option + " was not found.");
					throw new ShowNotFoundException(option);
				}

				System.out.println("Removing \"" + temp.getTitle() + "\" to your tracker.");
				if (!showDAO.removeShow(temp)) {
					System.out.println("Show Not Removed");
					throw new ShowNotTrackedException(option);
				}

				System.out.println("Show successfully removed!");

			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please input an integer option");
				sc.nextLine();
			} catch (ShowNotFoundException e) {
				System.out.println("Please try again.\n");
			} catch (ShowNotTrackedException e) {
				System.out.println("Error Occured :: Returning to main menu.");
				return;
			}

			if (!repeatAction("remove")) {
				System.out.println("Returning to main menu...");
				return;
			}
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
