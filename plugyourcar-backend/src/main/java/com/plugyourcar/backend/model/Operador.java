package com.plugyourcar.backend.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Operador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9113925340972823299L;

	@Id
	private Integer id;
	
	private String nombre;
	private String urlSitioWeb;
	private Boolean integrado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUrlSitioWeb() {
		return urlSitioWeb;
	}
	
	public void setUrlSitioWeb(String urlSitioWeb) {
		this.urlSitioWeb = urlSitioWeb;
	}

	public Boolean getIntegrado() {
		return integrado;
	}

	public void setIntegrado(Boolean integrado) {
		this.integrado = integrado;
	}

}
