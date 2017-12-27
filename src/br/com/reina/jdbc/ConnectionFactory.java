package br.com.reina.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/reina", "root", "password");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
