package com.revature.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			//We'll write some boilerplate to work with Properties
			Properties properties = new Properties();
			//The following lines just ensure we find connection.properties
			//regardless of how our project is built:
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			properties.load(loader.getResourceAsStream("connection.properties"));
			
			//All we've done is set these values to the values found in
			// connection.properties
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			//How to actually make connections with jdbc
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}