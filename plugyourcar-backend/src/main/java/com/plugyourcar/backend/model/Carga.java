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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JsonBackReference
	private Usuario usuario;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference
	private Conector conector;
	
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private Timestamp horaInicioReserva;
	private Timestamp horaFinReserva;
	private Timestamp horaDesconexion;
	private Boolean finalizada;
	private Boolean facturable;
	private Double cargoFactura;
	private Boolean cargaConReserva;
	private Double porcentaje;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private EstadoCarga estadoCarga;
	
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

	public Boolean getCargaConReserva() {
		return cargaConReserva;
	}

	public void setCargaConReserva(Boolean cargaConReserva) {
		this.cargaConReserva = cargaConReserva;
	}

	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public Timestamp getHoraInicioReserva() {
		return horaInicioReserva;
	}

	public void setHoraInicioReserva(Timestamp horaInicioReserva) {
		this.horaInicioReserva = horaInicioReserva;
	}

	public Timestamp getHoraFinReserva() {
		return horaFinReserva;
	}

	public void setHoraFinReserva(Timestamp horaFinReserva) {
		this.horaFinReserva = horaFinReserva;
	}

	public Timestamp getHoraDesconexion() {
		return horaDesconexion;
	}

	public void setHoraDesconexion(Timestamp horaDesconexion) {
		this.horaDesconexion = horaDesconexion;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}
