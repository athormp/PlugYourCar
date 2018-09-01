package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class OperacionNoAdmitidaException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conector;

	public OperacionNoAdmitidaException (String conector, StringBuilder message) {
		super(message.toString());
		conector = this.conector;
	}
	
}
