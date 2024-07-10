package jtc.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	private static Connection connection = null;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
