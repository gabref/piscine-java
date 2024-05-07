package edu._42roma.chat.repositories;

import java.sql.SQLException;
import java.util.List;

import edu._42roma.chat.models.User;

public interface UsersRepository {
	List<User> findAll(int page, int size) throws SQLException;
}
