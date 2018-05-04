package com.plugyourcar.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Password {
	
	@Id
	private int id;
	
	private String passwordEncriptada;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPasswordEncriptada() {
		return passwordEncriptada;
	}

	public void setPasswordEncriptada(String passwordEncriptada) {
		this.passwordEncriptada = passwordEncriptada;
	}
}
