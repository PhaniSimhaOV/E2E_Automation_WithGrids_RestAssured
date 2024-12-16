package com.autogrid.stepDefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.Assert;

public class databaseConnectionStepDefinition {
	private Connection connection;
	
	public void user_connects_to_mysql_database() {
		String url = "jdbc:mysql://localhost:3306";
		String username = "user";
		String password = "Mysqlkavita@123";
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful!");
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Database connection failed.");
		}
	}
		
	}
	
	
	
	
	

