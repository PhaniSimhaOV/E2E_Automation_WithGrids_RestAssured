package com.autogrid.steps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;


public class MysqlDatabaseConnectionPage {

	private Connection connection;

	public MysqlDatabaseConnectionPage(Connection connection) {
		this.connection = connection;
	}

	public String fetchData(int id) throws SQLException {

		String query = "SELECT * FROM dms_lead WHERE sales_consultant_id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {

					// Retrieve data from multiple columns
					Map<String, String> data = new HashMap<>();
					data.put("sales_consultant", resultSet.getString("sales_consultant"));
					data.put("lead_type", resultSet.getString("lead_type"));
					data.put("mode_of_payment", resultSet.getString("mode_of_payment"));
					data.put("finance_required", resultSet.getString("finance_required"));
					data.put("id", resultSet.getString("id"));
					data.put("first_name", resultSet.getString("first_name"));
					data.put("last_name", resultSet.getString("last_name"));
					data.put("lead_status", resultSet.getString("lead_status"));
					data.put("email", resultSet.getString("email"));
					data.put("phone", resultSet.getString("phone"));
					// Convert data map to a readable string
					return data.toString();

				} else {
					return "No data found for ID: " + id;
				}
			}
		}
	}
}
