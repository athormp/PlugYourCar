package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Id;

public class TipoConector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659617924491644697L;

	@Id
	private int id;
	
	private String descripcion;
	private String nombreFormal;
	private boolean descontinuado;
	private boolean obsoleto;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombreFormal() {
		return nombreFormal;
	}
	
	public void setNombreFormal(String nombreFormal) {
		this.nombreFormal = nombreFormal;
	}
	
	public boolean isDescontinuado() {
		return descontinuado;
	}
	
	public void setDescontinuado(boolean descontinuado) {
		this.descontinuado = descontinuado;
	}
	
	public boolean isObsoleto() {
		return obsoleto;
	}
	
	public void setObsoleto(boolean obsoleto) {
		this.obsoleto = obsoleto;
	}
}
