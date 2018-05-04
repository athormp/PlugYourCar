package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4055703750013940455L;

	@Id
	private int id;
	
	private Usuario usuario;
	private Conector conector;
	private Reserva reserva;
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private boolean finalizada;
	private boolean facturable;
	private double cargoFactura;
	
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
	
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
	
	public boolean isFinalizada() {
		return finalizada;
	}
	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	public boolean isFacturable() {
		return facturable;
	}
	
	public void setFacturable(boolean facturable) {
		this.facturable = facturable;
	}
	
	public double getCargoFactura() {
		return cargoFactura;
	}
	
	public void setCargoFactura(double cargoFactura) {
		this.cargoFactura = cargoFactura;
	}	
}
