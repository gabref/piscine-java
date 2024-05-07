package edu._42roma.chat;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
		String jdbcUrl = "jdbc:postgresql://localhost:5432/test_db";
		String username = "admin";
		String password = "root";

		try {
			Class.forName("org.postgresql.Driver");
			logger.info("Driver registered");
		} catch (Exception e) {
			logger.error("Could not register the postgresql driver");
			System.exit(1);
		}

		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			connection.close();
		} catch (Exception e) {
			logger.error("Connection went wrong");
			logger.error(e.getMessage());
		}
    }
}
