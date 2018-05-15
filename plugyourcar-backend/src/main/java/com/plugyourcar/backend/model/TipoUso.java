package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoUso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4527659972752401107L;

	@Id
	private Integer id;
	
	private String nombre;
	private Boolean pagoEnDestino;
	private Boolean suscripcionRequerida;
	private Boolean claveAcceso;
	
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
	
	public Boolean getPagoEnDestino() {
		return pagoEnDestino;
	}
	
	public void setPagoEnDestino(Boolean pagoEnDestino) {
		this.pagoEnDestino = pagoEnDestino;
	}
	
	public Boolean getSuscripcionRequerida() {
		return suscripcionRequerida;
	}
	
	public void setSuscripcionRequerida(Boolean suscripcionRequerida) {
		this.suscripcionRequerida = suscripcionRequerida;
	}
	
	public Boolean getClaveAcceso() {
		return claveAcceso;
	}
	
	public void setClaveAcceso(Boolean claveAcceso) {
		this.claveAcceso = claveAcceso;
	}
	
}
