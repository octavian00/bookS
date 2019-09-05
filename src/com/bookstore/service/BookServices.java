package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;

public class BookServices {
	private BookDAO bookDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	public BookServices( HttpServletRequest request, HttpServletResponse response,
			EntityManager entityManager) {
		super();
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		bookDAO=new BookDAO(entityManager);
		categoryDAO=new CategoryDAO(entityManager);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBook=bookDAO.listAll();
		request.setAttribute("listBooks", listBook);
		if(message!=null) {
			request.setAttribute("message", message);
		}
		String listPage="book_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	public void showNewBookForm() throws ServletException, IOException {
		List<Category> listCategory=categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		String newPage="book_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}
}
