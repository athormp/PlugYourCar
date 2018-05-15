package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 523643053772481925L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@NotNull
	private Timestamp horaLogin;
	
	private boolean exito = true;
	private String descripcionError;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
