package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;


import javax.persistence.EntityNotFoundException;

import javax.persistence.PersistenceException;
public class UserDAOTest extends BaseDAOTest {
	private static UserDAO userDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 BaseDAOTest.setUpBeforeClass();
		 userDAO=new UserDAO(entityManager);
	}
	
	@Test
	public void testCreateUsers() {
		Users user=new Users();
		user.setEmail("john@gmail.com");
		user.setFullName("John Smith");
		user.setPassword("jhonny");
		userDAO.create(user);
		assertTrue(user.getUserId()>0);
	}
	
	@Test(expected= PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user=new Users();
		userDAO.create(user);
		//assertTrue(user1.getUserId()>0);
	}
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("asd@gmail.com");
		user.setFullName("asdjaskd");
		user.setPassword("updated");
		user=userDAO.update(user);
		assertEquals("updated", user.getPassword());
		
	}
	@Test
	public void testGetUsersFound() {
		Integer userId=1;
		Users user=userDAO.get(userId);
		assertNotNull(user);
		
	}
	@Test
	public void testGetUserNotFound() {
		Integer userId=-1;
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test
	public void testDeleteUsers() {
		Integer userId=3;
		userDAO.delete(userId);
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteNonExitUsers() {
		Integer userId=-1;
		userDAO.delete(userId);
	}
	@Test
	public void testListAll() {
		List<Users> listUsers=userDAO.listAll();
		assertTrue(listUsers.size()>0);
	} 
	@Test
	public void testcountAll() {
		long totalUsers =userDAO.count();
		assertEquals(3, totalUsers);
	}
	@Test
	public void testFindByEmail() {
		String email="mihai@gmail.com";
		Users user=userDAO.findByEmail(email);
		assertNotNull(user);
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
