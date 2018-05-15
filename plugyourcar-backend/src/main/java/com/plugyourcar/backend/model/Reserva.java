package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Reserva implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -931120651124438557L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Conector conector;
	
	@NotNull
	private Timestamp horaInicio;
	
	private Timestamp horaFin;
	private Boolean cancelada;
	private Boolean cerrada;
	private Boolean facturable;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Boolean getCerrada() {
		return cerrada;
	}

	public void setCerrada(Boolean cerrada) {
		this.cerrada = cerrada;
	}

	public Boolean getFacturable() {
		return facturable;
	}

	public void setFacturable(Boolean facturable) {
		this.facturable = facturable;
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
