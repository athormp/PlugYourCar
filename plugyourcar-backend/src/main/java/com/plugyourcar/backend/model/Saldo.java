package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Saldo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5177469046873598700L;

	@Id
	private int id;
	
	private double cantidadDisponible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(double cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	
	
}