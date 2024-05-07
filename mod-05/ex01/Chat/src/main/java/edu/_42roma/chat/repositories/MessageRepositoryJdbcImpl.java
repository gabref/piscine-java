package edu._42roma.chat.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;

import edu._42roma.chat.models.Chatroom;
import edu._42roma.chat.models.Message;
import edu._42roma.chat.models.User;

public class MessageRepositoryJdbcImpl implements MessageRepository {
	private final Connection dataSource;

	public MessageRepositoryJdbcImpl(Connection dataSource) {
		this.dataSource = dataSource;
	}

	public Optional<Message> findById(Long id) throws SQLException {
		Statement statement = dataSource.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM chat.messages WHERE id = " + id);
		if (rs.next()) {
			long messageId = rs.getLong("id");
			long authorId = rs.getLong("author");
			long roomId = rs.getLong("chatroom");
			String messageText = rs.getString("text");
			Timestamp timestamp = rs.getTimestamp("datetime");

			ResultSet rsUser = statement.executeQuery("SELECT * FROM chat.users WHERE id = " + authorId);
			rsUser.next();
			User user = new User(rsUser.getLong("id"), rsUser.getString("login"), rsUser.getString("password"), null, null);

			ResultSet rsRoom = statement.executeQuery("SELECT * FROM chat.chatrooms WHERE id = " + roomId);
			rsRoom.next();
			Chatroom chatroom = new Chatroom(rsRoom.getLong("id"), rsRoom.getString("name"), null, null);
			Optional<Message> optionalMessage = Optional.of(new Message(messageId, user, chatroom, messageText, timestamp));
			return optionalMessage;
		} else {
			return Optional.empty();
		}
	}
}
