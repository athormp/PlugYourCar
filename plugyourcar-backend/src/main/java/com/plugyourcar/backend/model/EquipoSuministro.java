package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class EquipoSuministro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542062825195551469L;

	@Id
	private Integer id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference
	private PuntoCarga puntoCarga;
	
	private Integer referencia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TipoConector tipoConector;
	
	private Integer amperaje;
	private Integer voltaje;
	private Double potencia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TipoCargador tipoCargador;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TipoCorriente tipoCorriente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Tarifa tarifa;
	
	@OneToMany(mappedBy="equipoSuministro",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Conector> conectores;
	
	private Boolean admiteReserva;
	
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

	public Set<Conector> getConectores() {
		return conectores;
	}

	public void setConectores(Set<Conector> conectores) {
		this.conectores = conectores;
	}

	public Boolean getAdmiteReserva() {
		return admiteReserva;
	}

	public void setAdmiteReserva(Boolean admiteReserva) {
		this.admiteReserva = admiteReserva;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
}