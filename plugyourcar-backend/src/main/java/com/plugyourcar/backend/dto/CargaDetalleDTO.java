package com.plugyourcar.backend.dto;

import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EstadoCarga;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargaDetalleDTO {
	
	@JsonProperty
	private Integer id;
	@JsonProperty
	private Conector conector;
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
	
}
