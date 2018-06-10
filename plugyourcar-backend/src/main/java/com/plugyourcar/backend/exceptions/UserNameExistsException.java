package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class UserNameExistsException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameExistsException (String message) {
		super(message);
	}

}
