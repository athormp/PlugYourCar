package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Password implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9094120672573342631L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private String passwordEncriptada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordEncriptada() {
		return passwordEncriptada;
	}

	public void setPasswordEncriptada(String passwordEncriptada) {
		this.passwordEncriptada = passwordEncriptada;
	}
}
