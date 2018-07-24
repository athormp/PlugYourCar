package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plugyourcar.backend.model.Localizacion;
import com.plugyourcar.backend.model.Operador;
import com.plugyourcar.backend.model.TipoEstado;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntoCargaResumenDTO {
	
	@JsonProperty
	private Integer id;
	@JsonProperty
	private String uuid;
	@JsonProperty
	private Operador operador;
	@JsonProperty
	private String referenciaOperador;
	@JsonProperty
	private Localizacion localizacion;
	@JsonProperty
	private Integer numeroPuntos;
	@JsonProperty
	private TipoEstado tipoEstado;
	@JsonProperty
	private Integer estadoOcupacion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public String getReferenciaOperador() {
		return referenciaOperador;
	}

	public void setReferenciaOperador(String referenciaOperador) {
		this.referenciaOperador = referenciaOperador;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}
	
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public Integer getNumeroPuntos() {
		return numeroPuntos;
	}

	public void setNumeroPuntos(Integer numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}
	
	public TipoEstado getTipoEstado() {
		return tipoEstado;
	}

	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public Integer getEstadoOcupacion() {
		return estadoOcupacion;
	}

	public void setEstadoOcupacion(Integer estadoOcupacion) {
		this.estadoOcupacion = estadoOcupacion;
	}
	
}
