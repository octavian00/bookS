package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.entity.Category;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;

public class BookServices {
	private BookDAO bookDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CategoryDAO categoryDAO;
	public BookServices( HttpServletRequest request, HttpServletResponse response
			) {
		super();
		this.request = request;
		this.response = response;
		
		bookDAO=new BookDAO();
		categoryDAO=new CategoryDAO();
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

	public void createBook() throws ServletException, IOException {
		String title=request.getParameter("title");
		Book existBook=bookDAO.findByTitle(title);
		if(existBook!=null) {
			String message="Could not create new book because the title "+title+" already exists.";
			listBooks(message);
			return ;
		}
		
		Book newBook=new Book();
		readBookFields(newBook);
		Book createdBook=bookDAO.create(newBook);
		if(createdBook.getBookId()>0) {
			String message="A new book has been created succesfully.";
			//request.setAttribute("message", message);
			listBooks(message);
		}
	}

	public void editBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		String listPage="book_form.jsp";
		if(book!=null) {
			List<Category> listCategory=categoryDAO.listAll();
			request.setAttribute("book", book);
			request.setAttribute("listCategory", listCategory);
			
			
		}else {
			listPage="message.jsp";
			String message="Could not find book with ID"+ bookId;
		    request.setAttribute("message", message);
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	public void readBookFields(Book book) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=null;
		
		try {
		 publishDate=dateFormat.parse(request.getParameter("publishDate"));
		}catch(ParseException excetion) {
			//excetion.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category=categoryDAO.get(categoryId);
		book.setAuthor(author);
		book.setTitle(title);
		book.setDesciption(description);
		book.setCategory(category);
		book.setPublishDate(publishDate);
		book.setIsbn(isbn);
		book.setPrice(price);
		
		Part part=request.getPart("bookImage");
		if(part!=null &&part.getSize()>0) {
			long size=part.getSize();
			byte[] imageBytes=new byte[(int) size];
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}
	}
	public void updateBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		Book existBook=bookDAO.get(bookId);
		String title = request.getParameter("title");
		Book bookByTitle=bookDAO.findByTitle(title);
		if(bookByTitle != null&&!existBook.equals(bookByTitle)) {
			String message="Could not update the book because there's another book having same title.";
			listBooks(message);
			return;
		}
		readBookFields(existBook);
		bookDAO.update(existBook);
		String message="The book has been updated succesfully.";
		listBooks(message);
		
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		if(book==null) {
			String message="Could not find book with ID"+bookId+", or it might have been deleted";
			request.setAttribute("message", message);
			request.getRequestDispatcher(message).forward(request, response);
		}else {
			String message="Book has been deleted";
			bookDAO.delete(bookId);
			listBooks(message);
		}
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		List<Book> listBooks=bookDAO.listByCategory(categoryId);
		Category category=categoryDAO.get(categoryId);
	
		request.setAttribute("listBooks", listBooks);
		String listPage="frontend/books_list_by_category.jsp";
		if(category==null) {
			request.setAttribute("category", category);
			String message = "Sorry, the category ID " + categoryId + " is not available.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("frontend/message.jsp").forward(request, response);
			
			return;
		
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}

	public void viewBookDetail() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		String detailPage="frontend/book_detail.jsp";
		if(book!=null) {
			request.setAttribute("book", book);
		}else {
			detailPage="frontend/message.jsp";
			String message = "Sorry, the book with ID " + bookId + " is not available.";
			request.setAttribute("message", message);
			
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
		
	}

	public void search() throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<Book> result=null;
		if(keyword.equals("")) {
			result=bookDAO.listAll();
		}else {
			result=bookDAO.search(keyword);
		}
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		String resultPage="frontend/search_result.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}
}
