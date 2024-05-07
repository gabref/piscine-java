package edu._42roma.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu._42roma.chat.models.User;
import edu._42roma.chat.models.Chatroom;

public class UsersRepositoryJdbcImpl implements UsersRepository {
	private Connection conn;

	public UsersRepositoryJdbcImpl(Connection conn) {
		this.conn = conn;
	}

	public List<User> findAll(int page, int size) throws SQLException {
		List<User> users = new ArrayList<>();
		int offset = page * size;

		String sql = "WITH CreatedChatrooms AS (" +
				"    SELECT u.id AS user_id, u.login, u.password," +
				"           c.id AS chatroom_id, c.name AS chatroom_name, c.owner AS chatroom_owner," +
				"           cr.id AS socialroom_id, cr.name AS socialroom_name, cr.owner AS socialroom_owner" +
				"    FROM chat.users u" +
				"    LEFT JOIN chat.chatrooms c ON u.id = c.owner" +
				"    LEFT JOIN chat.chatrooms cr ON u.id != cr.owner" +
				")" +
				"SELECT user_id, login, password," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN chatroom_id IS NOT NULL THEN chatroom_id END), NULL) AS chatroom_ids," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN chatroom_name IS NOT NULL THEN chatroom_name END), NULL) AS chatroom_names," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN chatroom_owner IS NOT NULL THEN chatroom_owner END), NULL) AS chatroom_owners," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN socialroom_id IS NOT NULL THEN socialroom_id END), NULL) AS socialroom_ids," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN socialroom_name IS NOT NULL THEN socialroom_name END), NULL) AS socialroom_names," +
				"ARRAY_REMOVE(ARRAY_AGG(CASE WHEN socialroom_owner IS NOT NULL THEN socialroom_owner END), NULL) AS socialroom_owners" +
				" FROM CreatedChatrooms" +
				" GROUP BY user_id, login, password" +
				" ORDER BY user_id" +
				" LIMIT ? OFFSET ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, size);
			stmt.setInt(2, offset);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {

					long userId = rs.getLong("user_id");
					String login = rs.getString("login");
					String password = rs.getString("password");

					Integer[] chatroomIds = (Integer[]) rs.getArray("chatroom_ids").getArray();
					String[] chatroomNames = (String[]) rs.getArray("chatroom_names").getArray();
					// long[] chatroomOwners = (long[]) rs.getArray("chatroom_owners").getArray();
					Integer[] socialroomIds = (Integer[]) rs.getArray("socialroom_ids").getArray();
					String[] socialroomNames = (String[]) rs.getArray("socialroom_names").getArray();
					// long[] socialroomOwners = (long[]) rs.getArray("socialroom_owners").getArray();

					List<Chatroom> createdChatrooms = new ArrayList<Chatroom>();
					for (int i = 0; i < chatroomIds.length; i++)
						createdChatrooms.add(new Chatroom(
								 	Integer.toUnsignedLong(chatroomIds[i]), 
									chatroomNames[i], 
									null, null
								));

					List<Chatroom> socialRooms = new ArrayList<Chatroom>();
					for (int i = 0; i < socialroomIds.length; i++)
						socialRooms.add(new Chatroom(
									Integer.toUnsignedLong(socialroomIds[i]), 
									socialroomNames[i], null, null)
								);

					User user = new User(userId, login, password, createdChatrooms, socialRooms);
					users.add(user);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return users;
	}
}
