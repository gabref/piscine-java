package edu._42roma.chat.models;

import java.util.Objects;

public class Message {
	private final long id;
	private User author;
	private Chatroom room;
	private String text;
	private String datetime;

	public Message(long id, User author, Chatroom room, String text, String datetime) {
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.datetime = datetime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, author, room, text, datetime);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Message message = (Message) o;
		return id == message.getId() && author.equals(message.getAuthor()) && room.equals(message.getChatroom())
				&& text.equals(message.getText()) && datetime.equals(message.getDateTime());
	}

	public long getId() {
		return this.id;
	}

	public User getAuthor() {
		return author;
	}

	public Chatroom getChatroom() {
		return room;
	}

	public String getText() {
		return text;
	}

	public String getDateTime() {
		return datetime;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setChatroom(Chatroom chatroom) {
		this.room = chatroom;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDateTime(String dateTime) {
		this.datetime = dateTime;
	}
}
