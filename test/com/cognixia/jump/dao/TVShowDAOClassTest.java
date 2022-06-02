package com.cognixia.jump.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cognixia.jump.progress.tracker.User;

class TVShowDAOClassTest {

	// User object used to test methods
	public static User user;
	
	// TVShowDAOClass object used to test methods
	public static TVShowDAO showDAO;
	
	@BeforeAll
	public static void testSetUp() {
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream("default\n1234\n".getBytes()));
		
		
		PrintStream originalOut = System.out;
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        
        try {
			user = new User();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setIn(stdin);
		System.setOut(originalOut);
		
		try {
			os.close();
			ps.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showDAO = new TVShowDAOClass(user);
	}

	@BeforeEach
	public void setUpBetweenTests() {
		try {
			Statement stmt = user.getConn().createStatement();
			stmt.execute("TRUNCATE table user_shows");
			
			stmt.executeUpdate("INSERT INTO user_shows(user_id,show_id,status) VALUES (1, 2, 'NC'),"
					+ " (1, 1, 'C'), (1, 4, 'IP')");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	public static void tearDown() {
		try {
			Statement stmt = user.getConn().createStatement();
			stmt.execute("TRUNCATE table user_shows");
			
			stmt.close();
			user.exit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPrintAllTVShows() {
		String expectedOutput = "ID: 1, Title: Planet Earth II, Length: 04:58:00, Rating: 9.4"
				+ "ID: 2, Title: Breaking Bad, Length: 00:49:00, Rating: 9.4"
				+ "ID: 3, Title: Planet Earth, Length: 08:58:00, Rating: 9.4"
				+ "ID: 4, Title: Band of Brothers, Length: 09:54:00, Rating: 9.4"
				+ "ID: 5, Title: Chernobyl, Length: 05:30:00, Rating: 9.3"
				+ "ID: 6, Title: The Wire, Length: 60:00:00, Rating: 9.3"
				+ "ID: 7, Title: Blue Planet II, Length: 06:04:00, Rating: 9.2"
				+ "ID: 8, Title: Avatar: The Last Airbender, Length: 24:14:00, Rating: 9.2"
				+ "ID: 9, Title: Cosmos: A Spacetime Odyssey, Length: 09:17:00, Rating: 9.2"
				+ "ID: 10, Title: The Sopranos, Length: 86:00:00, Rating: 9.2";
		
		PrintStream originalOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		showDAO.printAllTVShows();
		
		assertEquals(expectedOutput, out.toString().replace("\n", "").replace("\r", ""));
		
		System.setOut(originalOut);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetAllUserShows() {
		String expectedOutput = "ID: 2, Title: Breaking Bad, Length: 00:49:00, Rating: 9.4"
				+ "ID: 1, Title: Planet Earth II, Length: 04:58:00, Rating: 9.4"
				+ "ID: 4, Title: Band of Brothers, Length: 09:54:00, Rating: 9.4";
		
		PrintStream originalOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		List<TVShow> showList = showDAO.getAllUserShows();
		
		for (TVShow show : showList) {
			System.out.println(show);
		}
		
		assertEquals(expectedOutput, out.toString().replace("\n", "").replace("\r", ""));
		
		System.setOut(originalOut);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetTVShowById() {
		TVShow show = showDAO.getTVShowById(2);
		
		String testTitle = "Breaking Bad";
		
		assertTrue(show.getTitle().equals(testTitle), "The titles are equal.");
	}

	@Test
	void testAddShow() {
		TVShow show = showDAO.getTVShowById(3);
		boolean added = showDAO.addShow(show);
		
		assertTrue(added, "The show was successfully added.");
		
		added = showDAO.addShow(show);
		
		assertFalse(added, "The show wasn't successfully added.");
	}

	@Test
	void testRemoveShow() {
		TVShow show = showDAO.getTVShowById(2);
		boolean removed = showDAO.removeShow(show);
		
		assertTrue(removed, "The show was successfully removed.");
		
		removed = showDAO.removeShow(show);
		
		assertFalse(removed, "The show was unsuccessfully removed.");
	}

	@Test
	void testAddNotCompleted() {
		TVShow show = showDAO.getTVShowById(2);
		boolean updated = showDAO.addNotCompleted(show);
		
		assertTrue(updated, "The show was successfully updated.");
	}

	@Test
	void testAddInProgress() {
		TVShow show = showDAO.getTVShowById(2);
		boolean updated = showDAO.addInProgress(show);
		
		assertTrue(updated, "The show was successfully updated.");
	}

	@Test
	void testAddCompleted() {
		TVShow show = showDAO.getTVShowById(2);
		boolean updated = showDAO.addCompleted(show);
		
		assertTrue(updated, "The show was successfully updated.");
	}

	@Test
	void testViewNotCompleted() {
		String expectedOutput = "ID: 2, Title: Breaking Bad, Length: 00:49:00, Rating: 9.4";
		
		PrintStream originalOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		showDAO.ViewNotCompleted();
		
		assertEquals(expectedOutput, out.toString().trim());
		
		System.setOut(originalOut);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewInProgress() {
		String expectedOutput = "ID: 4, Title: Band of Brothers, Length: 09:54:00, Rating: 9.4";
		
		PrintStream originalOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		showDAO.ViewInProgress();
		
		assertEquals(expectedOutput, out.toString().trim());
		
		System.setOut(originalOut);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewCompleted() {
		String expectedOutput = "ID: 1, Title: Planet Earth II, Length: 04:58:00, Rating: 9.4";
		
		PrintStream originalOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		showDAO.ViewCompleted();
		
		assertEquals(expectedOutput, out.toString().trim());
		
		System.setOut(originalOut);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
