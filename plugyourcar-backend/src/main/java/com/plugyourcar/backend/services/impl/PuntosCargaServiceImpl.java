package com.plugyourcar.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.dto.PuntoCargaResumenDTO;
import com.plugyourcar.backend.model.PuntoCarga;
import com.plugyourcar.backend.model.elasticsearch.GeoIndex;
import com.plugyourcar.backend.repositories.PuntoCargaRepository;
import com.plugyourcar.backend.repositories.elasticsearch.GeoIndexRepository;
import com.plugyourcar.backend.services.PuntosCargaService;


/* Clase de servicio responsable de obtener los datos de los puntos de carga
 */
@Service
public class PuntosCargaServiceImpl implements PuntosCargaService {
	
	@Autowired
	private GeoIndexRepository geoIndexRepository;
	
	@Autowired
	private PuntoCargaRepository puntoCargaRepository;
	
	Pageable pageable;

	// Método que permitirá recuperar los puntos de carga que estén dentro de un radio asociado a una posición
    public List<PuntoCargaResumenDTO> obtenerPuntosCarga(PosicionDTO posicionDTO) {
        
    	// Se forma el GeoPoint a partir de los parámetros de entrada
    	GeoPoint location = new GeoPoint(posicionDTO.getLatitud(), posicionDTO.getLongitud());
    	
    	/* Se recorre el índice elasticsearch que contiene todas las posiciones de los puntos de carga para recuperar 
    	** aquellos que entran dentro del radio entregado expresado en kms 
    	*/
    	Page<GeoIndex> geoIndexes = geoIndexRepository.findByLocationWithin(location, posicionDTO.getRadio()+"km", PageRequest.of(0,200));
    	
    	/* Se introducen los puntos de carga en una lista que contendrá todos los puntos de carga obtenidos con un detalle
        ** resumido de sus características
        */
    	List<PuntoCargaResumenDTO> puntosCargaResumenDTO = new ArrayList<PuntoCargaResumenDTO>();
    	for (GeoIndex geoIndex : geoIndexes) {
    		PuntoCarga puntoCarga = puntoCargaRepository.getOne(geoIndex.getPuntocarga());
    		ModelMapper modelMapper = new ModelMapper();
    		PuntoCargaResumenDTO puntoCargaResumenDTO = modelMapper.map(puntoCarga, PuntoCargaResumenDTO.class);
    		puntosCargaResumenDTO.add(puntoCargaResumenDTO);
    	}
        return puntosCargaResumenDTO;
    }
	
}