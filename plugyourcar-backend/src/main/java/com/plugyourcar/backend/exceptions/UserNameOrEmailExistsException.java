package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class UserNameOrEmailExistsException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dniUsuario;

	public UserNameOrEmailExistsException (String dniUsuario, StringBuilder message) {
		super(message.toString());
		this.dniUsuario = dniUsuario;
	}
}
