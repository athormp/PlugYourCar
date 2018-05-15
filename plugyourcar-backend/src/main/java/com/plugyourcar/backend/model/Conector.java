package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542062825195551469L;

	@Id
	private int id;
	
	private PuntoCarga punto;
	private int referencia;
	private TipoConector tipoConector;
	private int amperaje;
	private int voltaje;
	private int potencia;
	private TipoCargador tipoCargador;
	private TipoCorriente tipoCorriente;
	private EstadoConector estadoConector;
	
	@OneToMany(mappedBy="conector",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Reserva> reservas;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Carga> cargas;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public PuntoCarga getPunto() {
		return punto;
	}
	
	public void setPunto(PuntoCarga punto) {
		this.punto = punto;
	}
	
	public int getReferencia() {
		return referencia;
	}
	
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	
	public TipoConector getTipoConector() {
		return tipoConector;
	}
	
	public void setTipoConector(TipoConector tipoConector) {
		this.tipoConector = tipoConector;
	}
	
	public int getAmperaje() {
		return amperaje;
	}
	
	public void setAmperaje(int amperaje) {
		this.amperaje = amperaje;
	}
	
	public int getVoltaje() {
		return voltaje;
	}
	
	public void setVoltaje(int voltaje) {
		this.voltaje = voltaje;
	}
	
	public int getPotencia() {
		return potencia;
	}
	
	public void setPotencia(int potencia) {
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
	
	public EstadoConector getEstadoConector() {
		return estadoConector;
	}
	
	public void setEstadoConector(EstadoConector estadoConector) {
		this.estadoConector = estadoConector;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}