package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRatingNoReviews() {
		Book book=new Book();
		Set<Review> reviews=new HashSet<Review>();
		book.setReviews(reviews);
		float averageRating=book.getAverageRating();
		assertEquals(0.0, averageRating,0.0);
	}
	@Test
	public void testAverageRatingOneReview() {
		Book book=new Book();
		Set<Review> reviews=new HashSet<Review>();
		Review review1=new Review();
		review1.setRating(5);
		reviews.add(review1);
		book.setReviews(reviews);
		float averageRating=book.getAverageRating();
		assertEquals(5.0, averageRating,0.0);
	}
	@Test
	public void testAverageRatingThreeReviews() {
		Book book=new Book();
		Set<Review> reviews=new HashSet<Review>();
		Review review1=new Review();
		review1.setRating(5);
		reviews.add(review1);
		Review review2=new Review();
		review2.setRating(5);
		reviews.add(review2);
		Review review3=new Review();
		review3.setRating(5);
		reviews.add(review3);
		book.setReviews(reviews);
		float averageRating=book.getAverageRating();
		assertEquals(5.0, averageRating,0.0);
	}
	@Test
	public void testRatingStringOff() {
		float averageRating=0.0f;
		Book book=new Book();
		String ratingStar=book.getRatingString(averageRating);
		String expected="off,off,off,off,off";
		assertEquals(expected, ratingStar);
	}
	@Test
	public void testRatingStringOn() {
		float averageRating=5.0f;
		Book book=new Book();
		String ratingStar=book.getRatingString(averageRating);
		String expected="on,on,on,on,on";
		assertEquals(expected, ratingStar);
	}
	@Test
	public void testRatingStringHalf() {
		float averageRating=2.5f;
		Book book=new Book();
		String ratingStar=book.getRatingString(averageRating);
		String expected="on,on,half,off,off";
		assertEquals(expected, ratingStar);
	}
}
