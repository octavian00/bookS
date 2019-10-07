package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setEmail("customer@mail.com");
		customer.setFullName("Customer");
		customer.setPassword("customer");
		customer.setPhone("0764567891");
		customer.setAddress("nr 1 Mihai Viteazu");
		customer.setCity("Timisoara");
		customer.setCountry("Romania");
		customer.setZipcode("3306");
		Customer createdCustomer=customerDAO.create(customer);
		assertTrue(createdCustomer.getCustomerId()>0);
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

}
