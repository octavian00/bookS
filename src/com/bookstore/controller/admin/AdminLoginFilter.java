package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

   
    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpSession httpSession=httpServletRequest.getSession();
		boolean loggedIn=httpSession!=null&&httpSession.getAttribute("useremail")!=null;
		//pentru a evita dupa cedam login sa se reaplice filtrul eliminam pargina de login pentru filtru
		String loginURI=httpServletRequest.getContextPath()+"/admin/login";
		boolean loginRequest=httpServletRequest.getRequestURI().equals(loginURI);
		boolean loginPage=httpServletRequest.getRequestURI().endsWith("login.jsp");
		if(loggedIn && (loginRequest|| loginPage)) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else if(loggedIn ||loginRequest) {
			chain.doFilter(request, response);
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
