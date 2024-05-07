package edu._42roma.chat.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu._42roma.chat.models.Chatroom;
import edu._42roma.chat.models.Message;
import edu._42roma.chat.models.User;
import edu._42roma.chat.repositories.MessageRepositoryJdbcImpl;

/**
 * Hello world!
 *
 */
public class Program {
	static Logger logger = LoggerFactory.getLogger(Program.class);
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/test_db";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "root";

	private static Connection createDataSourceConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			logger.info("Driver registered");
		} catch (Exception e) {
			logger.error("Could not register the postgresql driver");
			System.exit(1);
		}
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(DB_URL);
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);

		HikariDataSource hikariDataSource = new HikariDataSource(config);
		if (hikariDataSource.getConnection() == null)
			throw new SQLException("Database connection failed");
		return hikariDataSource.getConnection();
	}

	public static void main(String[] args) {
		try {
			Connection conn = createDataSourceConnection();


			MessageRepositoryJdbcImpl messageRepository = new MessageRepositoryJdbcImpl(conn);
			Optional<Message> messageOptional = messageRepository.findById(Integer.toUnsignedLong(4));
			if (!messageOptional.isPresent()) {
				System.out.println("message not present");
				System.exit(0);
			}
			Message message = messageOptional.get();
			System.out.println("Message before\n" + message);

			message.setText("Bye");
			message.setDateTime(null);
			messageRepository.update(message);

			System.out.println("checking if message was updated!");
			System.out.println(messageRepository.findById((long)4).get());

		} catch (Exception e) {
			logger.error("Something went wrong, closing program");
			logger.error(e.getMessage());
			System.exit(1);
		}
	}
}
