package com.revature.project1.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String url = "jdbc:oracle:thin:"
			+ "@jd-proj1-db.clhqmcqu3mvx.us-east-2.rds.amazonaws.com"
			+ ":1521:ORCL";
	private static final String username = "sys_acct";
	private static final String password = "zN70EOPy#R(e";
	public static Connection getConnection() throws SQLException {
		try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return DriverManager.getConnection(url, username, password);
	}
}
