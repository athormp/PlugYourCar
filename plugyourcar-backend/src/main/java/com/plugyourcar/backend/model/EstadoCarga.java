package com.plugyourcar.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoCarga {
	
	@Id
	private Integer id;
	
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
