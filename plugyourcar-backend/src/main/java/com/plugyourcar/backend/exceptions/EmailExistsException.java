package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class EmailExistsException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailExistsException (String message) {
		super(message);
	}

}

