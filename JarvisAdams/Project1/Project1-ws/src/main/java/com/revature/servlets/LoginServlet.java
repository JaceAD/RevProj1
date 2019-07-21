package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	public void init(){
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		PrintWriter pw = resp.getWriter();
		
		boolean userexists = false;
		//Check that the user exists in the database
		if(userexists) {
			
		} else {
			
		}
		
		
	}
	
}
