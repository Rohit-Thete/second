package com.museum.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
	
	private static Connection con;
	
	public static Connection getDbConnection()throws SQLException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "password");
		return con;
		
	}

}
