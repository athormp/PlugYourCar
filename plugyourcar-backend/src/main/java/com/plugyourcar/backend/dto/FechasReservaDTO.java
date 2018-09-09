package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FechasReservaDTO {
	
	@JsonProperty("fechaInicio")
	private String fechaInicio;
	@JsonProperty("fechaFin")
    private String fechaFin;
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
