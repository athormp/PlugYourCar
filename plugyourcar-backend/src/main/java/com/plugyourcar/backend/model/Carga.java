package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Carga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4055703750013940455L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Conector conector;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Reserva reserva;
	
	@NotNull
	private Timestamp horaInicio;
	
	private Timestamp horaFin;
	private Boolean finalizada;
	private Boolean facturable;
	private Double cargoFactura;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public Boolean getFinalizada() {
		return finalizada;
	}
	
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	public Boolean getFacturable() {
		return facturable;
	}
	
	public void setFacturable(Boolean facturable) {
		this.facturable = facturable;
	}
	
	public Double getCargoFactura() {
		return cargoFactura;
	}
	
	public void setCargoFactura(Double cargoFactura) {
		this.cargoFactura = cargoFactura;
	}
	
}
