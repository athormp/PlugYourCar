package com.plugyourcar.backend.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargaResumenDTO {

	@JsonProperty
	private Integer id;
	@JsonProperty
	private String nombrePuntoCarga;
	@JsonProperty
	private Timestamp horaInicio;
	@JsonProperty
	private Timestamp horaFin;
	@JsonProperty
	private String estadoCarga;
	@JsonProperty
	private Boolean cargaConReserva;
	@JsonProperty
	private String operador;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombrePuntoCarga() {
		return nombrePuntoCarga;
	}
	public void setNombrePuntoCarga(String nombrePuntoCarga) {
		this.nombrePuntoCarga = nombrePuntoCarga;
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
	public String getEstadoCarga() {
		return estadoCarga;
	}
	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}
	public Boolean getCargaConReserva() {
		return cargaConReserva;
	}
	public void setCargaConReserva(Boolean cargaConReserva) {
		this.cargaConReserva = cargaConReserva;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	
}
