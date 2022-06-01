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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		System.setIn(stdin);
		System.setOut(originalOut);
	}

}
