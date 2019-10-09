package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.predicate.ExistsPredicate;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerDAO=new CustomerDAO();
	}
	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer=customerDAO.listAll();
		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer", listCustomer);
		String path="customer_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}
	public void createCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer=customerDAO.findByEmail(email);
		if(existCustomer!=null) {
			String message="Could not create a new customer :the  email "+email+ " is already registered by another customer";
			listCustomers(message);
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFields(newCustomer);
			customerDAO.create(newCustomer);
			String message="New customer has been created succesfully";
			listCustomers(message);
		}
	}
	public void registerCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer=customerDAO.findByEmail(email);
		String message="";
		if(existCustomer!=null) {
			message="Could not register :the  email "+email+ " is already registered by another customer";
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFields(newCustomer);
			customerDAO.create(newCustomer);
			 message="You have register succesfully! Thank you ";
		}
		request.setAttribute("message", message);
		String messagePage="frontend/message.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}
	public void editCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customer=customerDAO.get(customerId);
		request.setAttribute("customer", customer);
		String editPage="customer_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	public void updateCusotmer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		String email=request.getParameter("email");
		Customer existCustomer=customerDAO.findByEmail(email);
		String message="";
		if(existCustomer!=null && existCustomer.getCustomerId()!=customerId) {
			 message ="Could not update the customer ID"+customerId+" because there's an existing customer having the same email.";
		}else {
			String fullName=request.getParameter("fullName");
			String password=request.getParameter("password");
			String phone=request.getParameter("phoneNumber");
			String address=request.getParameter("adress");
			String city=request.getParameter("city");
			String zipCode=request.getParameter("zipCode");
			String country=request.getParameter("country");
			Customer customer=customerDAO.get(customerId);
			customer.setCustomerId(customerId);
			customer.setEmail(email);
			customer.setFullName(fullName);
			customer.setPassword(password);
			customer.setPhone(phone);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setZipcode(zipCode);
			customer.setCountry(country);
			customerDAO.update(customer);
			message="The customer has been updated succesfully.";
		}
		listCustomers(message);
	}
	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerId);
		String message="Customer has been deleted";
		listCustomers(message);
		
	}
	private void updateCustomerFields(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phoneNumber");
		String address = request.getParameter("adress");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		
		if (email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullName(fullName);
		
		if (password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipCode);
		customer.setCountry(country);		
	}
}
