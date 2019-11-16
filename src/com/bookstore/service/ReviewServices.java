package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.admin.book.NewBookServlet;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public ReviewServices(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		reviewDAO=new ReviewDAO();
	}
	public void listReviews(String message) throws ServletException, IOException {
		List<Review>listReview=reviewDAO.listAll();
		request.setAttribute("listReview", listReview);
		if(message!=null) {
			request.setAttribute("message", message);
		}
		String listReviews="review_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listReviews);
		requestDispatcher.forward(request, response);
	}
	public void listReviews() throws ServletException, IOException {
		listReviews(null);
	}
	public void deleteReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		Review review=reviewDAO.get(reviewId);
		String message="";
		if(review==null) {
			message="Could not find review with id"+reviewId+", or it might have been deleted";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return ;
		}
		reviewDAO.delete(reviewId);
		 message="Review has been deleted";
		listReviews(message);
		
		
	}
	public void showReviewForm() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookdDAO=new BookDAO();
		
		//System.out.println(bookId.toString());
		Book book=bookdDAO.get(bookId);
		request.setAttribute("book", book);
		request.getSession().setAttribute("book", book);
		HttpSession session=request.getSession();
		Customer customer=(Customer) session.getAttribute("loggedCustomer");
		Review exitReview=reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		String targetPage="frontend/review_form.jsp";
		if(exitReview!=null) {
			 request.setAttribute("review", exitReview);
			 targetPage="frontend/review_info.jsp";
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(targetPage);
		requestDispatcher.forward(request, response);
	}
	public void submitReview() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		Integer rating=Integer.parseInt(request.getParameter("rating"));
	    String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		Review newReview=new Review();
		newReview.setComment(comment);
		newReview.setHeadline(headline);
		newReview.setRating(rating);
		Book book=new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		reviewDAO.create(newReview);
		String messagePage="frontend/review_done.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}
	
}
