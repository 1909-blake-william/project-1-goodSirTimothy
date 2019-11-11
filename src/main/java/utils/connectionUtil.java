package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionUtil {
	public static Connection getConnection() throws SQLException {
		String hostname = System.getenv("DB_HOSTNAME");
		String port = System.getenv("DB_PORT");
		String dbName = System.getenv("DB_NAME");
		String username = System.getenv("PROJECT_1_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		return DriverManager.getConnection("jdbc:oracle:thin:@" + hostname + ":" + port + "/" + dbName, username, password);
	}
}
