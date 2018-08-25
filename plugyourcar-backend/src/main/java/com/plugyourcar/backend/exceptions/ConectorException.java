package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class ConectorException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conector;

	public ConectorException (String conector, StringBuilder message) {
		super(message.toString());
		conector = this.conector;
	}
	
}
