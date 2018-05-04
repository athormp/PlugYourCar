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
	private int id;
	
	private String nombre;
	private boolean pagoEnDestino;
	private boolean suscripcionRequerida;
	private boolean claveAcceso;
	
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
	
	public boolean isPagoEnDestino() {
		return pagoEnDestino;
	}
	
	public void setPagoEnDestino(boolean pagoEnDestino) {
		this.pagoEnDestino = pagoEnDestino;
	}
	
	public boolean isSuscripcionRequerida() {
		return suscripcionRequerida;
	}
	
	public void setSuscripcionRequerida(boolean suscripcionRequerida) {
		this.suscripcionRequerida = suscripcionRequerida;
	}
	
	public boolean isClaveAcceso() {
		return claveAcceso;
	}
	
	public void setClaveAcceso(boolean claveAcceso) {
		this.claveAcceso = claveAcceso;
	}
}
