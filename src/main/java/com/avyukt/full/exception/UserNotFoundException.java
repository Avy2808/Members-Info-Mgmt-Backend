package com.avyukt.full.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(long id) {
		super("Could Not Find User With User ID "+id);
	}
}
