package com.wipro.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	
	public static Connection getConnection()
	{
		try
		{
			 
			Properties props = new Properties();
			
			
			
			InputStream input = DBConnection.class
								.getClassLoader()
								.getResourceAsStream("db.properties");
			
			props.load(input);
			
			return DriverManager.getConnection(props.getProperty("url"),
					                           props.getProperty("username"),
					                           props.getProperty("password")
					);
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}