package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public CategoryServices( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;

		
		categoryDAO=new CategoryDAO();
	}
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory= categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if(message!=null) {
			request.setAttribute("message", message);
		}
		String listPage="category_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category exsitCategory=categoryDAO.findByName(name);
		if(exsitCategory!=null) {
			String message="Could not create category.A category with name"+name+"already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			Category category=new Category(name);
			categoryDAO.create(category);
			listCategory("New category created succesfully");
		}
	}
	public void editCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDAO.get(categoryId);
		String destPage="category_form.jsp";
		if(category==null) {
			destPage="message.jsp";
			String errorMessage="Could not find a category with id:"+categoryId;
			request.setAttribute("message", errorMessage);
		}else {
			request.setAttribute("category", category);
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	}
	public void updateCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("categoryId"));
		String name=request.getParameter("name");
		Category categoryById=categoryDAO.get(categoryId);
		Category categoryByName=categoryDAO.findByName(name);
		if(categoryByName!=null && categoryByName.getCategoryId()!=categoryById.getCategoryId()) {
			String message="Could not update the category with name "+name +" already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			Category updatedCategory=new Category(categoryId,name);
			categoryDAO.update(updatedCategory);
			String message="Category has been updated succesfully";
			listCategory(message);
		}
	}
	public void deleteCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO=new BookDAO();
		long numberOfBooks=bookDAO.countByCategory(categoryId);
		String message;
		if(numberOfBooks>0) {
			message="Could not delete the category (ID :%d) because it currently contains some books.";
			message=String.format(message, categoryId);
		}else {
			message="Category has been deleted";
			categoryDAO.delete(categoryId);
		}
		listCategory(message);
	}
	
}
