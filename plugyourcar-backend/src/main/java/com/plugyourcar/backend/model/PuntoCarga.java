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
public class PuntoCarga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5536272614814221877L;

	@Id
	private Integer id;
	
	@NotNull
	private String uuid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Operador operador;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TipoUso tipoUso;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Localizacion localizacion;
	
	private Integer numeroPuntos;
	private String referenciaOperador;
	private String costeUso;
	
	@OneToMany(mappedBy="puntoCarga",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<EquipoSuministro> equiposSuministro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getNumeroPuntos() {
		return numeroPuntos;
	}

	public void setNumeroPuntos(Integer numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}

	public String getReferenciaOperador() {
		return referenciaOperador;
	}

	public void setReferenciaOperador(String referenciaOperador) {
		this.referenciaOperador = referenciaOperador;
	}

	public String getCosteUso() {
		return costeUso;
	}

	public void setCosteUso(String costeUso) {
		this.costeUso = costeUso;
	}

	public List<EquipoSuministro> getEquiposSuministro() {
		return equiposSuministro;
	}

	public void setEquiposSuministro(List<EquipoSuministro> equiposSuministro) {
		this.equiposSuministro = equiposSuministro;
	}
	
}
