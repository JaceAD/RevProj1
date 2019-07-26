package com.revature.project1.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggedInFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(((HttpServletRequest)req).getSession(false) != null)
		{
			System.out.println("Passed servlet filter. User is logged into a session");
			arg2.doFilter(req, resp);
		}
		else
		{
			System.out.println("No session found. Checking in filter if it is a login authentication request");
			if(!((HttpServletRequest)req).getRequestURI().equals("/project1/servlet/login/authenticate"))
			{
				System.out.println("Could not find user. Redirecting to login.");
				((HttpServletResponse)resp).setStatus(401);
				//((HttpServletResponse)resp).sendRedirect("/project1/login.html");
			}
			else
			{
				System.out.println("This is a login authentication request. Passing control to servlet");
				arg2.doFilter(req, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
