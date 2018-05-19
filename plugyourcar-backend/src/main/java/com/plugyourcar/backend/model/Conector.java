package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Conector implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4382196184465459350L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	private EquipoSuministro equipoSuministro;
	
	@NotNull
	private Integer idReferencia;

	@ManyToOne(fetch=FetchType.LAZY)
	private EstadoConector estadoConector;

	@OneToMany(mappedBy="conector",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Reserva> reservas;
	
	@OneToMany(mappedBy="conector",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Carga> cargas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipoSuministro getEquipoSuministro() {
		return equipoSuministro;
	}

	public void setEquipoSuministro(EquipoSuministro equipoSuministro) {
		this.equipoSuministro = equipoSuministro;
	}

	public Integer getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(Integer idReferencia) {
		this.idReferencia = idReferencia;
	}

	public EstadoConector getEstadoConector() {
		return estadoConector;
	}

	public void setEstadoConector(EstadoConector estadoConector) {
		this.estadoConector = estadoConector;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}
	
}
