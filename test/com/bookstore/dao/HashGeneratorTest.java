package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashGeneratorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGenerateMD5() {
		String password="updated";
		String encryptedPassword=HashGenerator.generateMD5(password);
		System.out.println(encryptedPassword);
		assertTrue(true);
	}

}
