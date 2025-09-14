package com.bipin.UserService.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L; // default

	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}

}
