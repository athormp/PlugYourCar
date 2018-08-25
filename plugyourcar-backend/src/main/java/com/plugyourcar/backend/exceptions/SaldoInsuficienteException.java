package com.plugyourcar.backend.exceptions;

import java.io.Serializable;

public class SaldoInsuficienteException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String saldo;

	public SaldoInsuficienteException (String saldo, StringBuilder message) {
		super(message.toString());
		saldo = this.saldo;
	}

}
