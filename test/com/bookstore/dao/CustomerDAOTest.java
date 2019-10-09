package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

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
		Integer customerId=6;
		Customer customer=customerDAO.get(customerId);
		assertNotNull(customer);
	}
	@Test
	public void testUpdate() {
		Customer customer=customerDAO.get(6);
		String fullName="customerUpdate";
		customer.setFullName(fullName);		
		Customer updatedCustomer=customerDAO.update(customer);
		assertTrue(updatedCustomer.getFullName().equals(fullName));
	}
	@Test
	public void testDeleteCustomer() {
		Integer customerId=6;
		customerDAO.delete(customerId);
		Customer customer=customerDAO.get(6);
		assertNull(customer);
	}
	@Test
	public void testListAll() {
		List<Customer>listCustomers=customerDAO.listAll();
		assertTrue(listCustomers.size()>0);
	}
	@Test
	public void testCountAll() {
		long totalCustomer=customerDAO.count();
		assertEquals(1, totalCustomer);
	}
	@Test 
	public void testfindByEmail() {
		String email="customer@mail.com";
		Customer customer=customerDAO.findByEmail(email);
		assertNotNull(customer);
	}
}
