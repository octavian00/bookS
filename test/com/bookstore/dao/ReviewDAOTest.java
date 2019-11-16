package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {
	private static ReviewDAO reviewDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDAO=new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}
	@Test
	public void testCreateReview() {
		Review review =new Review();
		Book book=new Book();
		book.setBookId(13);
		Customer customer=new Customer();
		customer.setCustomerId(9);
		review.setCustomer(customer);
		review.setBook(book);
		review.setHeadline("client");
		review.setComment("client");
		review.setRating(4);
		Review createdReview=reviewDAO.create(review);
		assertTrue(createdReview.getReviewId()>0);
	}

	@Test
	public void testGet() {
		Integer reviewId=1;
		Review review=reviewDAO.get(reviewId);
		assertNotNull(review);
	}
	@Test
	public void testUpdateReview() {
		Integer reviewId=1;
		Review review=reviewDAO.get(reviewId);
		review.setHeadline("excellent");
		Review updatedReview=reviewDAO.update(review);
		assertEquals(review.getHeadline(),updatedReview.getHeadline());
	}
	@Test
	public void testListAll() {
		List<Review> listReview=reviewDAO.listAll();
		assertTrue(listReview.size()>0);
	}

	@Test
	public void testDeleteObject() {
		Integer reviewId=1;
		reviewDAO.delete(reviewId);
		Review review=reviewDAO.get(reviewId);
		assertNull(review);
	
	}

	@Test
	public void testCount() {
		long totalReview=reviewDAO.count();
		assertEquals(1, totalReview);
	}
	@Test
	public void testFindByCustomerAndBookNotFound() {
		Integer customerId=100;
		Integer bookId=99;
		Review result=reviewDAO.findByCustomerAndBook(customerId, bookId);
		assertNull(result);
	}
	@Test
	public void testFindByCustomerAndBookFound() {
		Integer customerId=19;
		Integer bookId=12;
		Review result=reviewDAO.findByCustomerAndBook(customerId, bookId);
		assertNotNull(result);
	}
}
