package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoConector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659617924491644697L;

	@Id
	private Integer id;
	
	@NotNull
	private String descripcion;
	
	private String nombreFormal;
	private Boolean descontinuado;
	private Boolean obsoleto;
	
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
	
	public String getNombreFormal() {
		return nombreFormal;
	}
	
	public void setNombreFormal(String nombreFormal) {
		this.nombreFormal = nombreFormal;
	}
	
	public Boolean getDescontinuado() {
		return descontinuado;
	}
	
	public void setDescontinuado(Boolean descontinuado) {
		this.descontinuado = descontinuado;
	}
	
	public Boolean getObsoleto() {
		return obsoleto;
	}
	
	public void setObsoleto(Boolean obsoleto) {
		this.obsoleto = obsoleto;
	}
	
}
