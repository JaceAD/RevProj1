package com.revature.DAOImps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.DAOs.RequestDAO;
import com.revature.beans.Account;
import com.revature.utilities.ConnectionFactory;

public class RequestDAOImp implements RequestDAO{

	@Override
	public void create(float reqAmount, String description, int status, int userID ) throws SQLException {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO Requests VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			//number, float, string, number
			ps.setFloat(1, reqAmount);
			ps.setString(2, description);
			ps.setInt(3, status);
			ps.setInt(4, userID);
		} catch (SQLException e) {
			System.out.println("Didn't create request.");
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Account getRequest(int requestID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRequest(int requestID, String requestee, float requestAmount, int receiptID, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRequest(int requestID) {
		// TODO Auto-generated method stub
		
	}

}
