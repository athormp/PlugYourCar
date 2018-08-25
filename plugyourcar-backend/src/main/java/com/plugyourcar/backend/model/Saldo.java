package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Saldo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5177469046873598700L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull 
	private Double cantidadDisponible;
	
	@OneToOne(mappedBy="saldo")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Double cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	
}