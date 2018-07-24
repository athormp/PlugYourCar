package com.plugyourcar.backend.model.elasticsearch;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "geoindex", type="posicion")
public class GeoIndex {

	@Id
	private Integer puntocarga;
	
    private GeoPoint location;

    /**
	 * @return the id
	 *//*
	public Integer get_id() {
		return _id;
	}

	*//**
	 * @param id the id to set
	 *//*
	public void set_id(Integer _id) {
		this._id = _id;
    }*/
    
	/*
	 * @return the puntoCarga
	 */
	public Integer getPuntocarga() {
		return puntocarga;
	}

	/*
	 * @param puntoCarga the puntoCarga to set
	 */
	public void setPuntocarga(Integer puntocarga) {
		this.puntocarga = puntocarga;
	}

	/**
	 * @return the location
	 */
	public GeoPoint getLocation() {
		return location;
	}

	/**
	 * @param location the geoPoint to set
	 */
	public void setLocation(GeoPoint location) {
		this.location = location;
	}

}