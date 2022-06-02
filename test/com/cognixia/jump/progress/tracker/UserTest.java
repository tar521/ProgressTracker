package com.cognixia.jump.progress.tracker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserTest {


	/**
	 * Validity of the connection to database with default login credentials
	 * is tested. The default input is passed to the user prompt. If successful,
	 * the username contained in user object should match the test parameters passed.
	 * The success of closing the connection is also tested. Implied that getUsername()
	 * also works correctly.
	 */
	@Test
	void testLogin() {
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream("default\n1234\n".getBytes()));
		
		
		PrintStream originalOut = System.out;
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        
        try {
			User user = new User();
			
			
			assertTrue(user.getUsername().equals("default"), "Login successfull");
			user.exit();
			assertTrue(user.getConn().isClosed(), "Connection successfully closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setIn(stdin);
		System.setOut(originalOut);
	}
}
