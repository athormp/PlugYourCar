package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Tarifa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3124675245118640670L;

	@Id
	private Integer id;
	
	@NotNull
	private Double precioKwh;
	
	@NotNull
	private String tipoCarga;
	
	private Double precioServicioMinimo;
	private Double precioServicioAdicional;
	private Timestamp fechaCreacion;
	private Timestamp fechaActualizacion;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getPrecioKwh() {
		return precioKwh;
	}
	
	public void setPrecioKwh(Double precioKwh) {
		this.precioKwh = precioKwh;
	}
	
	public String getTipoCarga() {
		return tipoCarga;
	}
	
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	
	public Double getPrecioServicioMinimo() {
		return precioServicioMinimo;
	}
	
	public void setPrecioServicioMinimo(Double precioServicioMinimo) {
		this.precioServicioMinimo = precioServicioMinimo;
	}
	
	public Double getPrecioServicioAdicional() {
		return precioServicioAdicional;
	}
	
	public void setPrecioServicioAdicional(Double precioServicioAdicional) {
		this.precioServicioAdicional = precioServicioAdicional;
	}
	
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}

