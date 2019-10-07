package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	private static CategoryDAO categoryDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO=new CategoryDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category category=new Category("Java");
		Category categoryCreated=categoryDAO.create(category);
		assertTrue(categoryCreated!=null && category.getCategoryId()>0);
	}
	@Test(expected= PersistenceException.class)
	public void testCreateCategoryFieldNotSet() {
		Category category=new Category();
		categoryDAO.create(category);
	}
	@Test
	public void testUpdateCategory() {
		Category category=new Category("math");
		category.setCategoryId(4);
		Category cat=categoryDAO.update(category);
		assertEquals(category.getName(), cat.getName());
	}

	@Test
	public void testGet() {
		Integer categoryId=2;
		Category category=categoryDAO.get(categoryId);
		assertNotNull(category);
	}
	@Test
	public void testGetCategoryNotFound() {
		Integer categoryId=-1;
		Category category=categoryDAO.get(categoryId);
		assertNull(category);
	}

	@Test
	public void testListAll() {
		List<Category> category=categoryDAO.listAll();
		assertTrue(category.size()>0);
	}

	@Test
	public void testDeleteObject() {
		Integer categoryId=2;
		categoryDAO.delete(categoryId);
		Category category=categoryDAO.get(categoryId);
		assertNull(category);
	}

	@Test
	public void testCount() {
		long totalCategories=categoryDAO.count();
		assertEquals(3, totalCategories);
	}
	@Test
	public void testFindByName() {
		String name="politics";
		Category category=categoryDAO.findByName(name);
		assertNotNull(category);
	}
	@Test
	public void testFindByNameNotFound() {
		String name="politics1";
		Category category=categoryDAO.findByName(name);
		assertNull(category);
	}
}
