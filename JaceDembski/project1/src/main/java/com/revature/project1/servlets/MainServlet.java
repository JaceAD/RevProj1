package com.revature.project1.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.beans.Employee;
import com.revature.project1.beans.ReimbursementRequest;
import com.revature.project1.daos.EmployeeDAO;
import com.revature.project1.daos.ReimbursementRequestDAO;

public class MainServlet extends HttpServlet {

	public static void printAttributes(HttpServletRequest req)
	{
		System.out.println("attribute names: ");
		java.util.Enumeration<String> attNames = req.getAttributeNames();
		while(attNames.hasMoreElements()) {
			System.out.println(attNames.nextElement());
		}
	}
	
	public static void printHeaders(HttpServletRequest req) {
		System.out.println("Header names: ");
		java.util.Enumeration<String> headNames = req.getHeaderNames();
		while(headNames.hasMoreElements()) {
			System.out.println(headNames.nextElement());
		}
	}
	
	public static void printParameters(HttpServletRequest req) {
		System.out.println("Parameter names: ");
		java.util.Enumeration<String> paraNames = req.getParameterNames();
		while(paraNames.hasMoreElements()) {
			System.out.println(paraNames.nextElement());
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DO GET
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Hello, this is the servlet's doGet");
		String lettucePath = req.getRequestURI();
		switch(lettucePath) {
		case "/project1/servlet/loadMyReq":
			System.out.println(req.getSession().getAttribute("userId").toString());
			respondWithReimReqsForCurrentEmp(req, resp);
			break;
		case "/project1/servlet/loadAllReq":
			respondWithReimReqsForAll(req,resp);
			break;
		case "/project1/servlet/redirReqSub":
			System.out.println("Redir to request submission");
			resp.sendRedirect("/project1/reimbursement_submission.html");
			break;
		case "/project1/servlet/redirInfoChange":
			System.out.println("Redir to info change");
			resp.sendRedirect("/project1/update_info.html");
			break;
		case "/project1/servlet/redirPassChange":
			System.out.println("Redir to password change");
			resp.sendRedirect("/project1/password_change.html");
			break;
		case "/project1/servlet/myData":
			System.out.println("Retrieving data for current user");
			respondWithCurrentUser(req, resp);
			break;
		case "/project1/servlet/loadReqDetail":
			respondWithSetReimReq(req, resp);
			break;
		case "/project1/servlet/loadAllEmps":
			respondWithAllEmps(req, resp);
			break;
		}
	}
	
	// DO POST
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Post request made");
		String lettucePath = req.getRequestURI();
		System.out.println("Found endpoint " + lettucePath);
		switch(lettucePath) {
		case "/project1/servlet/login/authenticate":
			verifyLogin(lettucePath, req, resp);
			break;
		case("/project1/servlet/requestSub"):
			submitReimReq(req, resp);
			break;
		case("/project1/servlet/updateEmp"):
			updateUser(req, resp);
			break;
		case("/project1/servlet/updatePass"):
			updatePassword(req, resp);
			break;
		case("/project1/servlet/loadReqPage"):
			req.getSession(false).setAttribute("ticketNo", req.getParameter("ticketNo"));
			printAttributes(req);
			printHeaders(req);
			printParameters(req);
			break;
		case("/project1/servlet/loadEmpReqs"):
			respondWithReimReqsForTargetEmployee(req, resp);
			break;
		}
	}
	
	/*
	 * 
	 * SERVICES
	 * 
	 */
	
	public void verifyLogin(String lettucePath, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("using login path. Redirecting");
		Employee emp = EmployeeDAO.getEmployeeById(Integer.parseInt(req.getParameter("username")));
		if(emp != null)
		{
			if(emp.getpWord().equals(req.getParameter("password"))) {
				req.getSession().setAttribute("userId", req.getParameter("username"));
				if(String.valueOf(emp.getAccessLvl()).toUpperCase().equals("B"))
				{
					resp.sendRedirect("/project1/employee_home.html");
				}
				else if(String.valueOf(emp.getAccessLvl()).toUpperCase().equals("M"))
				{
					resp.sendRedirect("/project1/manager_home.html");
				}
				
			} 
			else {
				resp.sendRedirect("/project1/login.html");
			}
		}
		else
		{
			resp.sendRedirect("/project1/login.html");
		}
	}
	
	public void submitReimReq(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		printAttributes(req);
		printHeaders(req);
		printParameters(req);
		System.out.println(req.getParameter("fileInput"));
		int empId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
		ReimbursementRequest reimReq = new ReimbursementRequest(1, empId, Integer.valueOf(req.getParameter("amount")),
				"pending", 0, req.getParameter("details"), LocalDateTime.now());
		ReimbursementRequestDAO.addRequest(reimReq);
		resp.sendRedirect("/project1/employee_home.html"); // TODO Use servlet to choose between emp and manager
	}
	
	public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Updating user");
		int empId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
		Employee emp = EmployeeDAO.getEmployeeById(empId);
		emp.setfName(req.getParameter("FirstName"));
		emp.setlName(req.getParameter("LastName"));
		emp.setEmail(req.getParameter("Email"));
		emp.setAccessLvl(req.getParameter("AccessLevel").charAt(0));
		EmployeeDAO.updateEmployee(emp);
		System.out.println("redirecting back");
		resp.sendRedirect("/project1/update_info.html");
	}
	
	public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int empId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
		Employee emp = EmployeeDAO.getEmployeeById(empId);
		if(req.getParameter("CurrentPW").equals(emp.getpWord())) {
			if(req.getParameter("NewPW").equals(req.getParameter("ConfirmPW"))) {
				emp.setpWord(req.getParameter("NewPW"));
				EmployeeDAO.updateEmployee(emp);
				resp.sendRedirect("/project1/update_info.html");
			}
			else
			{
				System.out.println("Passwords do not match");
			}
		}
		else
		{
			System.out.println("The password does not match what is on record");
		}
	}
	
	public void respondWithCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int empId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
		Employee emp = EmployeeDAO.getEmployeeById(empId);
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(emp));
	}
	
	public void respondWithAllEmps(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(EmployeeDAO.getAllEmployees()));
	}
	
	public void respondWithReimReqsForCurrentEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(
				ReimbursementRequestDAO.getRequestsByEmpId(
						Integer.parseInt(req.getSession().getAttribute("userId").toString())
						)));
	}
	
	public void respondWithReimReqsForAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(ReimbursementRequestDAO.getAllRequests()));
	}
	
	public void respondWithReimReqsForTargetEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(
				ReimbursementRequestDAO.getRequestsByEmpId(
						Integer.parseInt(req.getParameter("empId").toString())
						)));
	}
	
	public void respondWithSetReimReq(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int ticket = Integer.parseInt(req.getSession().getAttribute("ticketNo").toString());
		ReimbursementRequest reimReq = ReimbursementRequestDAO.getRequestByTicket(ticket);
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(reimReq));
	}
}
