package com.plugyourcar.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntoCargaDTO {
	
	@JsonProperty("ID")
	private Integer id;
	@JsonProperty("UUID")
	private String uuid;
	@JsonProperty("OperatorID")
	private Integer operador;
	@JsonProperty("OperatorsReference")
	private String referenciaOperador;
	@JsonProperty("UsageTypeID")
	private Integer tipoUso;
	@JsonProperty("AddressInfo")
	private LocalizacionDTO localizacioDTO;
	@JsonProperty("NumberOfPoints")
	private Integer numeroPuntos;
	@JsonProperty("UsageCost")
	private String costeUso;
	@JsonProperty("Connections")
	private List<ConectorDTO> conectoresDTO;
	@JsonProperty("StatusTypeID")
	private Integer tipoEstadoDTO;
	@JsonProperty("DateLastStatusUpdate")
	private String ultimaFechaActualizacion;
	@JsonProperty("DateCreated")
	private String fechaCreacion;
	
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

	public Integer getOperador() {
		return operador;
	}

	public void setOperador(Integer operador) {
		this.operador = operador;
	}

	public String getReferenciaOperador() {
		return referenciaOperador;
	}

	public void setReferenciaOperador(String referenciaOperador) {
		this.referenciaOperador = referenciaOperador;
	}

	public Integer getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(Integer tipoUso) {
		this.tipoUso = tipoUso;
	}

	public LocalizacionDTO getLocalizacioDTO() {
		return localizacioDTO;
	}
	
	public void setLocalizacioDTO(LocalizacionDTO localizacioDTO) {
		this.localizacioDTO = localizacioDTO;
	}

	public Integer getNumeroPuntos() {
		return numeroPuntos;
	}

	public void setNumeroPuntos(Integer numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}

	public String getCosteUso() {
		return costeUso;
	}

	public void setCosteUso(String costeUso) {
		this.costeUso = costeUso;
	}

	public List<ConectorDTO> getConectoresDTO() {
		return conectoresDTO;
	}

	public void setConectoresDTO(List<ConectorDTO> conectoresDTO) {
		this.conectoresDTO = conectoresDTO;
	}
	
	public Integer getTipoEstadoDTO() {
		return tipoEstadoDTO;
	}

	public void setTipoEstadoDTO(Integer tipoEstadoDTO) {
		this.tipoEstadoDTO = tipoEstadoDTO;
	}

	public String getUltimaFechaActualizacion() {
		return ultimaFechaActualizacion;
	}

	public void setUltimaFechaActualizacion(String ultimaFechaActualizacion) {
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
    public String toString() {
        return "PuntoCarga {" +
                "ID=" + id +
                ", UUID='" + uuid + '\'' +
                '}';
    }
	
}
