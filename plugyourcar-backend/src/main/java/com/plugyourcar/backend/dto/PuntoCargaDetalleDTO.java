package com.plugyourcar.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plugyourcar.backend.model.EquipoSuministro;
import com.plugyourcar.backend.model.Localizacion;
import com.plugyourcar.backend.model.Operador;
import com.plugyourcar.backend.model.TipoEstado;
import com.plugyourcar.backend.model.TipoUso;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntoCargaDetalleDTO {
	
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
	@JsonProperty
	private TipoUso tipoUso;
	@JsonProperty
	private List<EquipoSuministro> equiposSuministro;
	
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

	public TipoUso getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}

	public List<EquipoSuministro> getEquiposSuministro() {
		return equiposSuministro;
	}

	public void setEquiposSuministro(List<EquipoSuministro> equiposSuministro) {
		this.equiposSuministro = equiposSuministro;
	}
	
}