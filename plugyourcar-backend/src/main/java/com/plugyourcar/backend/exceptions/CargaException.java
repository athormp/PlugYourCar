package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class CargaException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conector;

	public CargaException (String conector, StringBuilder message) {
		super(message.toString());
		conector = this.conector;
	}
	
}
