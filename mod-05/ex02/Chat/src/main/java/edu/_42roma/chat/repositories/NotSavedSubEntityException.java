package edu._42roma.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {
	@Override
	public String getMessage() {
		return ": No such sub entity";
	}
}
