package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizacionDTO {

	@JsonProperty("ID")
	private int id;
	@JsonProperty("Title")
	private String nombre;
	@JsonProperty("AddressLine1")
	private String direccion;
	@JsonProperty("Town")
	private String localidad;
	@JsonProperty("StateOrProvince")
	private String provincia;
	@JsonProperty("PostCode")
	private String codigoPostal;
	@JsonProperty("Latitude")
	private double latitud;
	@JsonProperty("Longitude")
	private double longitud;
	@JsonProperty("ContactTelephone1")
	private String telefonoContacto;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
}
