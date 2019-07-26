package com.revature.project1.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.beans.ReimbursementRequest;

public class ReimbursementRequestDAO {
	
	
	public static ReimbursementRequest getRequestByTicket(int ticket) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT TICKET_NO, EMP_ID, AMOUNT, STATUS, RESOLVED_BY, DETAILS, LAST_UPDATE "
					+ "FROM REIMBURSEMENT_REQUESTS WHERE TICKET_NO=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, ticket);
			ResultSet results = st.executeQuery();
			ReimbursementRequest reim = null;
			while(results.next()) {
				reim = new ReimbursementRequest(results.getInt("TICKET_NO"), results.getInt("EMP_ID"),
						results.getInt("AMOUNT"), results.getString("STATUS"),
						results.getInt("RESOLVED_BY"), results.getString("DETAILS"),
						results.getTimestamp("LAST_UPDATE").toLocalDateTime());
			}
			return reim;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<ReimbursementRequest> getAllRequests() {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT TICKET_NO, EMP_ID, AMOUNT, STATUS, RESOLVED_BY, DETAILS, LAST_UPDATE "
					+ "FROM REIMBURSEMENT_REQUESTS";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet results = st.executeQuery();
			
			ArrayList<ReimbursementRequest> reims = new ArrayList<ReimbursementRequest>();
			while(results.next()) {
				reims.add(new ReimbursementRequest(results.getInt("TICKET_NO"), results.getInt("EMP_ID"),
						results.getInt("AMOUNT"), results.getString("STATUS"),
						results.getInt("RESOLVED_BY"), results.getString("DETAILS"),
						results.getTimestamp("LAST_UPDATE").toLocalDateTime())
						);
			}
			return reims;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<ReimbursementRequest> getRequestsByEmpId(int empId)
	{
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT TICKET_NO, EMP_ID, AMOUNT, STATUS, RESOLVED_BY, DETAILS, LAST_UPDATE "
					+ "FROM REIMBURSEMENT_REQUESTS WHERE EMP_ID=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, empId);
			ResultSet results = st.executeQuery();
			
			ArrayList<ReimbursementRequest> reims = new ArrayList<ReimbursementRequest>();
			while(results.next()) {
				reims.add(new ReimbursementRequest(results.getInt("TICKET_NO"), results.getInt("EMP_ID"),
						results.getInt("AMOUNT"), results.getString("STATUS"),
						results.getInt("RESOLVED_BY"), results.getString("DETAILS"),
						results.getTimestamp("LAST_UPDATE").toLocalDateTime())
						);
			}
			return reims;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public static void addRequest(ReimbursementRequest reim) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			System.out.println(reim.getEmpId());
			String sql = "INSERT INTO REIMBURSEMENT_REQUESTS (TICKET_NO, EMP_ID, AMOUNT, STATUS, RESOLVED_BY, DETAILS, LAST_UPDATE) "
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, reim.getTicketNo());
			st.setInt(2, reim.getEmpId());
			st.setInt(3, reim.getAmount());
			st.setString(4, reim.getStatus());
			st.setInt(5, reim.getResolvedBy());
			st.setString(6, reim.getDetails());
			st.setDate(7, Date.valueOf(LocalDate.now()));
			st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateRequest(ReimbursementRequest reim) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			System.out.println(reim.getEmpId());
			String sql = "UPDATE REIMBURSEMENT_REQUESTS SET "
					+ "EMP_ID = ?, AMOUNT = ?, STATUS = ?, RESOLVED_BY = ?, DETAILS = ?, LAST_UPDATE = ? "
					+ " WHERE TICKET_NO = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, reim.getEmpId());
			st.setInt(2, reim.getAmount());
			st.setString(3, reim.getStatus());
			st.setInt(4, reim.getResolvedBy());
			st.setString(5, reim.getDetails());
			st.setDate(6, Date.valueOf(LocalDate.now()));
			
			st.setInt(7, reim.getTicketNo());
			st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
