package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PuntoCarga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5536272614814221877L;

	@Id
	private int id;
	
	private String uuid;
	private Operador operador;
	private TipoUso tipoUso;
	private Localizacion localizacion;
	private int numeroPuntos;
	private String referenciaOperador;
	private Timestamp fechaUltimaActualizacion;
	private String costeUso;
	
	@OneToMany(mappedBy="puntoCarga",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Conector> conectores;
	
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
	
	public Operador getOperador() {
		return operador;
	}
	
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public TipoUso getTipoUso() {
		return tipoUso;
	}
	
	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}
	
	public Localizacion getLocalizacion() {
		return localizacion;
	}
	
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	
	public int getNumeroPuntos() {
		return numeroPuntos;
	}
	
	public void setNumeroPuntos(int numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}
	
	public String getReferenciaOperador() {
		return referenciaOperador;
	}
	
	public void setReferenciaOperador(String referenciaOperador) {
		this.referenciaOperador = referenciaOperador;
	}
	
	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	
	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	
	public String getCosteUso() {
		return costeUso;
	}
	
	public void setCosteUso(String costeUso) {
		this.costeUso = costeUso;
	}	
}
