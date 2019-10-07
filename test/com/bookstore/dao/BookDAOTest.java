package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest  {
	private static BookDAO bookDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO=new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book book=new Book();
		Category category=new Category("Python");
		category.setCategoryId(5);
		book.setCategory(category);
		book.setTitle("Effective Java (2nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDesciption("New coverage of generics, enums, annotations, autoboxing");
		book.setPrice(38.87f);
		book.setIsbn("0321356683");
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/28/2008");
		book.setPublishDate(publishDate);
		String imagePath="C:\\Users\\lenovo\\eclipse-workspace\\bookstore\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		Book createdBook =bookDAO.create(book);
		assertTrue(createdBook.getBookId()>0);
	}
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book book=new Book();
		book.setBookId(1);
		Category category=new Category("education");
		category.setCategoryId(10);
		book.setCategory(category);
		book.setTitle("Effective Java (3rd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDesciption("New coverage of generics, enums, annotations, autoboxing");
		book.setPrice(40f);
		book.setIsbn("0321356683");
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/28/2008");
		book.setPublishDate(publishDate);
		String imagePath="C:\\Users\\lenovo\\eclipse-workspace\\bookstore\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		Book updatedBook=bookDAO.update(book);
		assertEquals(updatedBook.getTitle(),"Effective Java (3rd Edition)");
		
	}
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId=100;
		bookDAO.delete(bookId);
	}
	@Test
	public void testDeleteBookSucces() {
		Integer bookId=1;
		bookDAO.delete(bookId);
		assertTrue(true);
	}
	@Test
	public void testGetBookSucces() {
		Integer bookId=7;
		Book book=bookDAO.get(bookId);
		assertNotNull(book);
	}
	@Test
	public void testGetBookFail() {
		Integer bookId=-1;
		Book book=bookDAO.get(bookId);
		assertNull(book);
	}
	@Test
	public void testBookListAll() {
		List<Book> listBook=bookDAO.listAll();
		assertTrue(listBook.size()>0);
		
	}
	@Test
	public void testfindByTitleNotExist() {
		String title="Thinkin in Java";
		Book book=bookDAO.findByTitle(title);
		assertNull(book);
	}

	@Test
	public void testfindByTitleFound() {
		String title="Effective Java (2nd Edition)";
		Book book=bookDAO.findByTitle(title);
		assertNotNull(book);
	}
	@Test 
	public void testCount() {
		int  totalBooks=(int) bookDAO.count();
		assertEquals(1, totalBooks);
	}
	@Test
	public void testListByCategory() {
		int categoryId=3;
		List<Book> listBooks=bookDAO.listByCategory(categoryId);
		assertTrue(listBooks.size()>0);
	}
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks=bookDAO.listNewBooks();
		assertEquals(4, listNewBooks.size());
	}
	@Test
	public void testSearchBookInTitle() {
		String keyword="Java";
		List<Book> result =bookDAO.search(keyword);
		assertEquals(4, result.size());
	}
	@Test
	public void testSearchBookInAuthor() {
		String keyword="Bert Bates";
		List<Book> result =bookDAO.search(keyword);
		assertEquals(2, result.size());
	}
	@Test
	public void testSearchBookInDescription() {
		String keyword="New coverage";
		List<Book> result =bookDAO.search(keyword);
		assertEquals(1, result.size());
	}
	@Test
	public void testCountByCategory() {
		Integer categoryId=3;
		long numOfBooks=bookDAO.countByCategory(categoryId);
		assertEquals(6,numOfBooks);
	}
}
