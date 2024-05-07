package edu._42roma.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public Long save(Message message) throws SQLException {
		try {
			PreparedStatement preparedStatement = this.dataSource.prepareStatement("INSERT INTO chat.messages(author, chatroom, text, datetime) VALUES (?, ?, ?, ?);");
			preparedStatement.setLong(1, message.getAuthor().getId());
			preparedStatement.setLong(2, message.getChatroom().getId());
			preparedStatement.setString(3, message.getText());
			preparedStatement.setTimestamp(4, message.getDateTime());
			preparedStatement.executeUpdate();

			PreparedStatement idStatement = this.dataSource.prepareStatement("SELECT id FROM chat.messages WHERE datetime = ?;");
			idStatement.setTimestamp(1, message.getDateTime());
			ResultSet rs = idStatement.executeQuery();
			rs.next();
			Long messageId = rs.getLong("id");
			message.setId(messageId);

			return messageId;

		} catch (SQLException e) {
			throw new NotSavedSubEntityException();
		}
	}
}
