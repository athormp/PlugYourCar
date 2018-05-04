package com.plugyourcar.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Localizacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1085498877054815292L;

	@Id
	private int id;
	
	private String nombre;
	private String direccion1;
	private String direccion2;
	private String localidad;
	private String provincia;
	private String codigoPostal;
	private double latitud;
	private double longitud;
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
	
	public String getDireccion1() {
		return direccion1;
	}
	
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	
	public String getDireccion2() {
		return direccion2;
	}
	
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
