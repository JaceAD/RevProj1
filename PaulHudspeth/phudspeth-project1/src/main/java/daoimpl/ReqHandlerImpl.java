package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Request;
import daointer.ReqHandlerInter;

public class ReqHandlerImpl implements ReqHandlerInter
{

	private Connection conn;
	
	public  ReqHandlerImpl()
	{
		try 
		{
			conn = ConnectionManager.getConnection();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Connection getDBConnection() 
	{
		return this.conn;
	}

	@Override
	public List<Request> getAllRequests() 
	{
		List<Request> requests = new ArrayList<>();
		try {
			String sql = "SELECT * FROM requests";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				int empID = results.getInt("emp_id");
				int manID=results.getInt("manager_id");
				double amount=results.getDouble("amount");
				int requestStatus=results.getInt("request_status");
				String desc=results.getString("req_description"); 
				int cat=results.getInt("req_category");
				int ticketNumb=results.getInt("ticket_no");
				requests.add(new Request(empID, manID, amount, requestStatus, desc, cat, ticketNumb));
			}
			
			return requests;
		} catch (SQLException e) {
			
			return requests;
		}
	}

	@Override
	public List<Request> getUserRequest(int userID) 
	{
		List<Request> requests = new ArrayList<>();
		try {
			String sql = "SELECT * FROM requests WHERE emp_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				int empID = results.getInt("emp_id");
				int manID=results.getInt("manager_id");
				double amount=results.getDouble("amount");
				int requestStatus=results.getInt("request_status");
				String desc=results.getString("req_description"); 
				int cat=results.getInt("req_category");
				int ticketNumb=results.getInt("ticket_no");
				requests.add(new Request(empID, manID, amount, requestStatus, desc, cat, ticketNumb));
			}
			
			return requests;
		} catch (SQLException e) {
			
			return requests;
		}
	}
	
	@Override
	public List<Request> getAllResolved() 
	{
		List<Request> requests = new ArrayList<>();
		try {
			String sql = "SELECT * FROM requests WHERE emp_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 2);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				int empID = results.getInt("emp_id");
				int manID=results.getInt("manager_id");
				double amount=results.getDouble("amount");
				int requestStatus=results.getInt("request_status");
				String desc=results.getString("req_description"); 
				int cat=results.getInt("req_category");
				int ticketNumb=results.getInt("ticket_no");
				requests.add(new Request(empID, manID, amount, requestStatus, desc, cat, ticketNumb));
			}
			
			return requests;
		} catch (SQLException e) {
			
			return requests;
		}
	}

	@Override
	public List<Request> getAllSubmitted() 
	{
		List<Request> requests = new ArrayList<>();
		try {
			String sql = "SELECT * FROM requests WHERE emp_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				int empID = results.getInt("emp_id");
				int manID=results.getInt("manager_id");
				double amount=results.getDouble("amount");
				int requestStatus=results.getInt("request_status");
				String desc=results.getString("req_description"); 
				int cat=results.getInt("req_category");
				int ticketNumb=results.getInt("ticket_no");
				requests.add(new Request(empID, manID, amount, requestStatus, desc, cat, ticketNumb));
			}
			
			return requests;
		} catch (SQLException e) {
			
			return requests;
		}
	}

	@Override
	public void updateStatus(Request req) 
	{
		try 
		{
			String sql = "UPDATE requests SET request_status = ? WHERE ticket_no = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req.getRequestStatus());
			stmt.setInt(2, req.getTicketNumb());
			stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateRequest(Request req) 
	{
		try 
		{
			String sql = "UPDATE requests SET request_status = ? WHERE ticket_no = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req.getRequestStatus());
			stmt.setInt(2, req.getTicketNumb());
			stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void createRequest(Request req) 
	{
		
	}

}
