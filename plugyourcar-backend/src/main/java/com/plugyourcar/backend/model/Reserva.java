package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reserva implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -931120651124438557L;

	@Id
	private int id;
	
	private Usuario usuario;
	private Conector conector;
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private boolean cancelada;
	private boolean cerrada;
	private boolean facturable;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Conector getConector() {
		return conector;
	}
	
	public void setConector(Conector conector) {
		this.conector = conector;
	}
	
	public Timestamp getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Timestamp getHoraFin() {
		return horaFin;
	}
	
	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}
	
	public boolean isCancelada() {
		return cancelada;
	}
	
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	public boolean isCerrada() {
		return cerrada;
	}
	
	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}
	
	public boolean isFacturable() {
		return facturable;
	}
	
	public void setFacturable(boolean facturable) {
		this.facturable = facturable;
	}
}
