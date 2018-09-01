package com.plugyourcar.backend.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plugyourcar.backend.model.EstadoCarga;
import com.plugyourcar.backend.model.TipoConector;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargaDetalleDTO {
	
	@JsonProperty
	private Integer id;
	@JsonProperty
	private String nombrePuntoCarga;
	@JsonProperty
	private String operador;
	@JsonProperty
	private String referencia;
	@JsonProperty
	private Timestamp horaInicio;
	@JsonProperty
	private Timestamp horaFin;
	@JsonProperty
	private Timestamp horaInicioReserva;
	@JsonProperty
	private Timestamp horaFinReserva;
	@JsonProperty
	private Timestamp horaDesconexion;
	@JsonProperty
	private Boolean finalizada;
	@JsonProperty
	private Double cargoFactura;
	@JsonProperty
	private Boolean cargaConReserva;
	@JsonProperty
	private Integer porcentaje;
	@JsonProperty
	private EstadoCarga estadoCarga;
	@JsonProperty
	private TipoConector tipoConector;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public Boolean getFinalizada() {
		return finalizada;
	}
	
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
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
	
	public Integer getPorcentaje() {
		return porcentaje;
	}
	
	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}
	
	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public String getNombrePuntoCarga() {
		return nombrePuntoCarga;
	}

	public void setNombrePuntoCarga(String nombrePuntoCarga) {
		this.nombrePuntoCarga = nombrePuntoCarga;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public TipoConector getTipoConector() {
		return tipoConector;
	}

	public void setTipoConector(TipoConector tipoConector) {
		this.tipoConector = tipoConector;
	}
	
}
