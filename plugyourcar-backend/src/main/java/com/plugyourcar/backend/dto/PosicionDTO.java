package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PosicionDTO {

	@JsonProperty("latitud")
	private Double latitud;
	@JsonProperty("longitud")
    private Double longitud;
    @JsonProperty("radio")
    private Integer radio;
    
	public Double getLatitud() {
		return latitud;
	}
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public Double getLongitud() {
		return longitud;
	}
	
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
    }
    
    public Integer getRadio() {
		return radio;
	}
	
	public void setRadio(Integer radio) {
		this.radio = radio;
    }

}