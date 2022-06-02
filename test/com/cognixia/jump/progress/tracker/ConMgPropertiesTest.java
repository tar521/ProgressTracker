package com.cognixia.jump.progress.tracker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConMgPropertiesTest {

	@Test
	void testGetConnection() {
		
        Connection conn = ConMgProperties.getConnection();
        
        assertFalse(conn == null, "Connection to SQL Server is successfull established");
	}

}
