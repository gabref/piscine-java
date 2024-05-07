package edu._42roma.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
	private final long id;
	private final String login;
	private String password;
	private List<Chatroom> createdRooms;
	private List<Chatroom> socializeRooms;

	public User(long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializeRooms) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.createdRooms = createdRooms;
		this.socializeRooms = socializeRooms;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, createdRooms, socializeRooms);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;

		return id == user.getId() && login.equals(user.getLogin()) &&
				password.equals(user.getPassword()) && createdRooms.equals(user.getCreatedRooms())
				&& socializeRooms.equals(user.getSocializeRooms());
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", createdRooms=" + createdRooms +
				", socializeRooms=" + socializeRooms +
				'}';
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public List<Chatroom> getCreatedRooms() {
		return createdRooms;
	}

	public List<Chatroom> getSocializeRooms() {
		return socializeRooms;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public void setCreatedRooms(List<Chatroom> createdRooms) {
		this.createdRooms = createdRooms;
	}

	public void setSocializeRooms(List<Chatroom> socializeRooms) {
		this.socializeRooms = socializeRooms;
	}
}
