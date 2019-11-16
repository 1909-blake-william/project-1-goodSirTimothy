package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is for getting the JDBC connection to the database
 * @author Tim Clifton
 *
 */
public class connectionUtil {
	/**
	 * Load the jdbc driver
	 */
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String hostname = System.getenv("DB_HOSTNAME");
		String port = System.getenv("DB_PORT");
		String dbName = System.getenv("DB_NAME");
		String username = System.getenv("PROJECT_1_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		return DriverManager.getConnection("jdbc:oracle:thin:@" + hostname + ":" + port + "/" + dbName, username, password);
	}
}
