package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 523643053772481925L;

	@Id
	private int id;
	
	private Usuario usuario;
	private Timestamp horaLogin;
	private boolean exito;
	private String descripcionError;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getHoraLogin() {
		return horaLogin;
	}

	public void setHoraLogin(Timestamp horaLogin) {
		this.horaLogin = horaLogin;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
}
