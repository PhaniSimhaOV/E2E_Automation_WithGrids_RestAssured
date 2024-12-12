package com.autogrid.steps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					return resultSet.getString("sales_consultant");
				} else {
					return "No data found for ID: " + id;
				}
			}
		}
	}
}
