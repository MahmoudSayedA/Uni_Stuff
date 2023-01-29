package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * connector class used to sequel server database connection
 * for different databases
 */
/**
 * @author Mahmoud
 *
 */
public class DatabaseConnector {
	// database URL
	private String dbUrl = "jdbc:sqlserver://localhost;encrypt=false;integratedSecurity=true;databaseName=";
	// Instance from connection class
	private Connection conn;

	public DatabaseConnector(String databaseName) {
		this.dbUrl += (databaseName + ";");
	}

	// connect with specific database and return it
	public synchronized Connection connect() {
		try {
			// establish the connection
			conn = DriverManager.getConnection(dbUrl);
			System.out.println("database connected");
		} catch (SQLException e) {
			System.out.println("database connect fail");
			e.printStackTrace();
		}
		return this.conn;
	}

	// disconnect to free resources
	public boolean disConnect() {
		try {
			this.conn.close();
			System.out.println("database disconnected");

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
