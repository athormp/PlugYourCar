package com.plugyourcar.backend.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference
	private EquipoSuministro equipoSuministro;
	
	@NotNull
	private Integer idReferencia;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private EstadoConector estadoConector;

	@OneToMany(mappedBy="conector",fetch=FetchType.LAZY)
	@JsonManagedReference
	private Set<Reserva> reservas;
	
	@OneToMany(mappedBy="conector",fetch=FetchType.LAZY)
	@JsonManagedReference
	private Set<Carga> cargas;

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

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Set<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(Set<Carga> cargas) {
		this.cargas = cargas;
	}
	
}
