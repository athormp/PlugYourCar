package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoCargador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949017772983043767L;

	@Id
	private Integer id;
	
	private String nombre;
	private String descripcion;
	
	@NotNull
	private Boolean cargaRapida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getCargaRapida() {
		return cargaRapida;
	}

	public void setCargaRapida(Boolean cargaRapida) {
		this.cargaRapida = cargaRapida;
	}
	
}
