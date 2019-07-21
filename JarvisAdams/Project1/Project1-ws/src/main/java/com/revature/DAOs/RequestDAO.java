package com.revature.DAOs;

import java.sql.SQLException;

import com.revature.beans.Account;

public interface RequestDAO {
	
	//CREATE
	public void create(float reqAmount, String description, int userID , int status) throws SQLException;
	
	//READ
	public Account getRequest(int requestID);
	
	//UPDATE
	public void updateRequest(int requestID, String requestee, float requestAmount, int receiptID, int status);
	
	//DELETE
	public void deleteRequest(int requestID);
}
