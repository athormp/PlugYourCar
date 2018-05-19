package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EquipoSuministro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542062825195551469L;

	@Id
	private Integer id;
	
	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	private PuntoCarga puntoCarga;
	
	private Integer referencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TipoConector tipoConector;
	
	private Integer amperaje;
	private Integer voltaje;
	private Double potencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TipoCargador tipoCargador;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TipoCorriente tipoCorriente;
	
	@OneToMany(mappedBy="equipoSuministro",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Conector> Conectores;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public PuntoCarga getPuntoCarga() {
		return puntoCarga;
	}
	
	public void setPuntoCarga(PuntoCarga puntoCarga) {
		this.puntoCarga = puntoCarga;
	}
	
	public Integer getReferencia() {
		return referencia;
	}
	
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	
	public TipoConector getTipoConector() {
		return tipoConector;
	}
	
	public void setTipoConector(TipoConector tipoConector) {
		this.tipoConector = tipoConector;
	}
	
	public Integer getAmperaje() {
		return amperaje;
	}
	
	public void setAmperaje(Integer amperaje) {
		this.amperaje = amperaje;
	}
	
	public Integer getVoltaje() {
		return voltaje;
	}
	
	public void setVoltaje(Integer voltaje) {
		this.voltaje = voltaje;
	}
	
	public Double getPotencia() {
		return potencia;
	}
	
	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}
	
	public TipoCargador getTipoCargador() {
		return tipoCargador;
	}
	
	public void setTipoCargador(TipoCargador tipoCargador) {
		this.tipoCargador = tipoCargador;
	}
	
	public TipoCorriente getTipoCorriente() {
		return tipoCorriente;
	}
	
	public void setTipoCorriente(TipoCorriente tipoCorriente) {
		this.tipoCorriente = tipoCorriente;
	}

	public List<Conector> getConectores() {
		return Conectores;
	}

	public void setConectores(List<Conector> conectores) {
		Conectores = conectores;
	}
}