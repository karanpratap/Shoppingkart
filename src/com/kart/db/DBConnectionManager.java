package com.kart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	static{
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/studentdb", "kps", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
