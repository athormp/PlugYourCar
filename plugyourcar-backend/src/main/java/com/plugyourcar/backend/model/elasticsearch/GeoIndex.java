package com.plugyourcar.backend.model.elasticsearch;

import javax.persistence.Id;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

@Document(indexName = "geoindex", type="posicion")
public class GeoIndex {

	@Id
    private Integer puntoCarga;

    @GeoPointField
    private GeoPoint location;

    /**
	 * @return the puntoCarga
	 */
	public Integer puntoCarga() {
		return puntoCarga;
	}

	/**
	 * @param puntoCarga the geoPoint to set
	 */
	public void setpuntoCarga(Integer puntoCarga) {
		this.puntoCarga = puntoCarga;
    }
    
	/**
	 * @return the location
	 */
	public GeoPoint getlocation() {
		return location;
	}

	/**
	 * @param location the geoPoint to set
	 */
	public void setlocation(GeoPoint location) {
		this.location = location;
	}

}