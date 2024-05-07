package edu._42roma.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
	private Long id;
	private User author;
	private Chatroom room;
	private String text;
	private Timestamp datetime;

	public Message(Long id, User author, Chatroom room, String text, Timestamp datetime) {
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

	@Override
    public String toString() {
        return "Message : {" +
                "\n\tid=" + id +
                ",\n\tauthor=" + author +
                ",\n\tchatroom=" + room +
                ",\n\ttext='" + text + '\'' +
                ",\n\tdateTime=" + datetime +
                "\n}";
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

	public Timestamp getDateTime() {
		return datetime;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDateTime(Timestamp dateTime) {
		this.datetime = dateTime;
	}
}
