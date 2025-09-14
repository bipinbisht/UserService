package com.bipin.UserService.exception;

public class PasswordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L; // default

	public PasswordNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public PasswordNotFoundException(String msg) {
		super(msg);
	}

}
