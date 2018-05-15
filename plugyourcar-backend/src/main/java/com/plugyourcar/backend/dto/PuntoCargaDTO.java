package com.plugyourcar.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntoCargaDTO {
	
	@JsonProperty("ID")
	private int id;
	@JsonProperty("UUID")
	private String uuid;
	@JsonProperty("OperatorID")
	private int operador;
	@JsonProperty("OperatorsReference")
	private String referenciaOperador;
	@JsonProperty("UsageTypeID")
	private int tipoUso;
	@JsonProperty("AddressInfo")
	private LocalizacionDTO localizacioDTO;
	@JsonProperty("NumberOfPoints")
	private int numeroPuntos;
	@JsonProperty("UsageCost")
	private String costeUso;
	@JsonProperty("Connections")
	private List<ConectorDTO> conectoresDTO;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public int getOperador() {
		return operador;
	}
	
	public void setOperador(int operador) {
		this.operador = operador;
	}
	
	public String getReferenciaOperador() {
		return referenciaOperador;
	}
	
	public void setReferenciaOperador(String referenciaOperador) {
		this.referenciaOperador = referenciaOperador;
	}
	
	public int getTipoUso() {
		return tipoUso;
	}
	
	public void setTipoUso(int tipoUso) {
		this.tipoUso = tipoUso;
	}
	
	public LocalizacionDTO getLocalizacioDTO() {
		return localizacioDTO;
	}
	
	public void setLocalizacioDTO(LocalizacionDTO localizacioDTO) {
		this.localizacioDTO = localizacioDTO;
	}
	
	public int getNumeroPuntos() {
		return numeroPuntos;
	}
	
	public void setNumeroPuntos(int numeroPuntos) {
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
	
	@Override
    public String toString() {
        return "PuntoCarga {" +
                "ID=" + id +
                ", UUID='" + uuid + '\'' +
                '}';
    }
	
}
