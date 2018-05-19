package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoEstado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8155580334341192629L;
	
	@Id
	private Integer id;
	
	private Boolean enFuncionamiento;
	private Boolean seleccionablePorUsuario;
	
	@NotNull
	private String nombre;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getEnFuncionamiento() {
		return enFuncionamiento;
	}
	
	public void setEnFuncionamiento(Boolean enFuncionamiento) {
		this.enFuncionamiento = enFuncionamiento;
	}
	
	public Boolean getSeleccionablePorUsuario() {
		return seleccionablePorUsuario;
	}

	public void setSeleccionablePorUsuario(Boolean seleccionablePorUsuario) {
		this.seleccionablePorUsuario = seleccionablePorUsuario;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
