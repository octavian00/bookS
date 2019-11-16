package com.bookstore.controller.frontend;

import java.io.IOException;
import java.net.HttpRetryException;

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

@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final String[] LoginRequiredURLs={
		"/view_profile","/edit_profile","/update_profile","/write_review"
	};
    public CustomerLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpSession session=httpServletRequest.getSession(false);
		String path=httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		String requestURL=httpServletRequest.getRequestURL().toString();
		boolean loggedIn=session!=null && session.getAttribute("loggedCustomer")!=null;
		if(!loggedIn && isLoginRequired(requestURL)) {
			String queryString=httpServletRequest.getQueryString();
			String redirectURL=requestURL;
			if(queryString!=null) {
				redirectURL=redirectURL.concat("?").concat(queryString);
			}
			session.setAttribute("redirectURL", redirectURL);
			String loginPage="frontend/login.jsp";
			RequestDispatcher requestDispatcher =request.getRequestDispatcher(loginPage);
			requestDispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired(String requestURL) {
		for(String loginRequiredURL:LoginRequiredURLs) {
			if(requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
