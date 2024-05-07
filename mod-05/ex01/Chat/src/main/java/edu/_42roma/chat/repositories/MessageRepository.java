package edu._42roma.chat.repositories;

import java.sql.SQLException;
import java.util.Optional;

import edu._42roma.chat.models.Message;

public interface MessageRepository {
	Optional<Message> findById(Long id) throws SQLException;
}