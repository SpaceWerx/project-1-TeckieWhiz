package com.revature.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtil {
	
	/** String constant for the commonly used POSTGRE JDBC driver */
    public static final String DRIVER_POSTGRE = "org.postgresql.Driver";
   
    public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	private static ConnectionFactoryUtil connectionFactory = null;
	
    private ConnectionFactoryUtil() 
    {
    		
    	URL= "jdbc:postgresql://java-fullstack-aws-project1.czjznkcexrmy.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=public";
    	USERNAME = "postgres";
    	PASSWORD = "password";
    	//URL= "jdbc:postgresql://localhost:5432/CLIDb?currentSchema=public";
    	//USERNAME = "postgres";
    	//PASSWORD = "admin";
    }
    
    public Connection createConnection() 
    {
		try 
		{
			Class.forName(DRIVER_POSTGRE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to load Driver");
		}

		try 
		{
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static synchronized Connection getConnection() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactoryUtil();
		}
		return connectionFactory.createConnection();
	}
    
    
    
}
