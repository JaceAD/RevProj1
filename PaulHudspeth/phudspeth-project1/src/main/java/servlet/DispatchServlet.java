package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employee;
import daoimpl.RegImpl;
import daointer.RegistrarInterface;
import services.LoginService;

/**
 * Servlet implementation class DispatchServlet
 */

public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RegistrarInterface dao;
    
    @Override
    public void init() throws ServletException 
    {
    	this.dao = new RegImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String doing= request.getRequestURI();
		System.out.println(doing);
		response.setStatus(201);
		switch(doing) 
		{
		case"/jcindustries/app/login":
			List<Employee> emps = dao.getAllEmp();
			String username = request.getParameter("username");
			System.out.println(username);
			String password = request.getParameter("password");
			System.out.println(password);
			System.out.println(LoginService.userAuthenticate(emps, username, password));
			if(LoginService.userAuthenticate(emps, username, password))
			{
				System.out.println("We have a working servlet!");
			}
			else 
			{
				System.out.println("Our servlet es no bueno...");
			}
		}
		//response.sendRedirect("jcindustries/");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		RegImpl des = (RegImpl) dao;
		try {
			des.getDBConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
