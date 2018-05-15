package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class EstadoConector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2404565595661638384L;

	@Id
	private Integer id;
	
	@NotNull
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
