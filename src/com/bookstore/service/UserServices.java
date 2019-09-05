package com.bookstore.service;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManager;


public class UserServices {
	private EntityManager entityManager;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public UserServices(EntityManager entityManager,HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		this.entityManager=entityManager;
		userDAO=new UserDAO(entityManager);
	}
	public void listUser() throws ServletException, IOException {
		listUser(null);
	}
	public void listUser(String message) throws ServletException, IOException {
		List<Users>listUsers=userDAO.listAll();
		request.setAttribute("listUsers", listUsers);
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		String listPage="user_list.jsp";
		RequestDispatcher requestDispacther=request.getRequestDispatcher(listPage);
		requestDispacther.forward(request, response);
	}
	
	public void createUser() throws ServletException, IOException {
		String email=request.getParameter("email");
		String fullName=request.getParameter("fullname");
		String password=request.getParameter("password");
		Users existUser=userDAO.findByEmail(email);
		if(existUser!=null) {
			String message="Could not create user.An user with email "+email+" already exists ";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else {
			try {
				String encryptedPassword=HashGenerator.generateMD5(password);
				Users user=new Users(email,fullName,encryptedPassword);
				userDAO.create(user);
				listUser("New user created succesfully");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
	public void editUser() throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("id"));
		Users user=userDAO.get(userId);
		String destPage="user_form.jsp";
		if(user==null) {
			destPage="message.jsp";
			String errorMessage="Could not find user with ID "+userId;
			request.setAttribute("message", errorMessage);
		}else {
			request.setAttribute("user", user);
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
		
	}
	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users userById = userDAO.get(userId);
		
		Users userByEmail = userDAO.findByEmail(email);
		
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user. User with email " + email + " already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
			
		} else {
			userById.setEmail(email);
			userById.setFullName(fullName);
			
			if (password != null && !password.isEmpty()) {
				String encryptedPassword = HashGenerator.generateMD5(password);
				userById.setPassword(encryptedPassword);				
			}
			
			userDAO.update(userById);

			String message = "User has been updated successfully";
			listUser(message);
		}
	}
	public void deleteUser() throws ServletException, IOException {
		
		int userId=Integer.parseInt(request.getParameter("id"));
		if(userId==1) {
			String message="The defauld admin user account cannot be deleted";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			userDAO.delete(userId);
			String message="User has been deleted successfully";
			listUser(message);
		}
	}
	public void login() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String encryptedPassword=HashGenerator.generateMD5(password);
		boolean loginResult=userDAO.checkLogin(email, password);
		//System.out.println(loginResult);
		if(loginResult) {
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else {
			String message="Login failed!";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
