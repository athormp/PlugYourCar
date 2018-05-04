package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoCargador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949017772983043767L;

	@Id
	private int id;
	
	private String nombre;
	private String descripcion;
	private boolean cargaRapida;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	
	public boolean isCargaRapida() {
		return cargaRapida;
	}
	
	public void setCargaRapida(boolean cargaRapida) {
		this.cargaRapida = cargaRapida;
	}
}
