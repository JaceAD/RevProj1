package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public ConnectionFactory(){
		
	}
	
	public static Connection getConnection() throws SQLException {
		String port = "1521";
		String hostname = "project-1.cvriicvj5pb8.us-east-2.rds.amazonaws.com"; //a.k.a. endpoint
		String dbName = "ORCL";
		String admin_username = "project-1";
		String admin_password = "Project111";
		
		String url_ds = "jdbc:oracle:thin:@"
				+hostname
				+":"+port
				+":"+dbName;
		//-----------
		//Test for getting Connection without potentially destroying the connection on exit of this function.
		Connection c;
		try {
			c = DriverManager.getConnection(url_ds, admin_username, admin_password);
		}catch(SQLException e) {
			System.out.println("Couldn't establish connection!");
			throw e;
		}
		return c;
	}
}
