package com.revature.DAOs;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Account;

public interface AccountDAO {
	
	//CREATE
	public void create(String userName, String password, int type, String name, String email) throws SQLException;
	
	//READ
	public Account getAccount(int userID);
	
	public Account getAccount(String userName);
	
	public List<Account> getAllAccounts();
	
	public boolean isAccount(String userID);
	
	//UPDATE
	public void updateAccount(String userName, String name, String password, String email);
	
	//DELETE
	public void deleteAccount(String userName);

	
}
