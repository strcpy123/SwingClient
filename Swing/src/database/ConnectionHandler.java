package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for Database Connection
 */
public class ConnectionHandler {
	private static Connection con = null;

	/**
	 * Returns connection object
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// load the Driver Class
		Class.forName("com.mysql.jdbc.Driver");
		// create the connection now
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "papa9973");
		return con;
	}
}