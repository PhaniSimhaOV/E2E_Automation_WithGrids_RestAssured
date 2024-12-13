package com.autogrid.stepDefinitions;

import java.sql.Connection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.autogrid.steps.MysqlDatabaseConnectionPage;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionStepDefinition {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private MysqlDatabaseConnectionPage mysqlDatabaseConnectionPage;

	@Given("User connects to MySQL Workbench database")
	public void user_connects_to_mysql_workbench_database() {
		try {
			String url = "jdbc:mysql://pentesting-db.chvrsbdweoe1.ap-south-1.rds.amazonaws.com/salesDataSetup?enabledTLSProtocols=TLSv1.2";
			String username = "dualPunch";
			String password = "Cyepro@123";

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful!");
			mysqlDatabaseConnectionPage = new MysqlDatabaseConnectionPage(connection); // Initialize
		} catch (SQLException e) {
			System.err.println("Error connecting to the Database: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Database connection failed.");
		}
	}

	@When("User executes a query to fetch data")
	public void user_executes_query_to_fetch_data() {
		try {
			String result = mysqlDatabaseConnectionPage.fetchData(874); // Fetch data for ID 1
			System.out.println("Query Result: " + result);
		} catch (SQLException e) {
			System.err.println("Error executing the query: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Query execution failed.");
		}
	}

	@Then("User closes the database connection")
	public void user_closes_the_database_connection() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (connection != null)
				connection.close();
			System.out.println("Database connection closed successfully.");
		} catch (SQLException e) {
			System.err.println("Error closing the database connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
