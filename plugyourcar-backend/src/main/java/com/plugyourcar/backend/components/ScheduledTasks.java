package com.plugyourcar.backend.components;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.plugyourcar.backend.dto.PuntoCargaDTO;
import com.plugyourcar.backend.services.CargaService;
import com.plugyourcar.backend.services.PuntosCargaLoaderService;

/* Clase responsable de mantener métodos que se ejecutarán con una cierta frecuencia, como por ejempo la carga y actualización de los datos
 * de los puntos de carga que se obtendrán por llamada REST de Open Charge Map 
 */

@Component
public class ScheduledTasks {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PuntosCargaLoaderService puntosCargaLoader;
	
	@Autowired
	private CargaService cargaService;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	 
	@Transactional
	@Scheduled(fixedRate = 3600000)
	public void loadPOIFromOCM() {
		log.debug("Llamada REST");
		PuntoCargaDTO[] puntosCargaDTO = restTemplate.getForObject("http://api.openchargemap.io/v2/poi/?output=json&countrycode=ES&maxresults=1000&compact=true&verbose=false&includecomments=false&opendata=true", PuntoCargaDTO[].class);
		puntosCargaLoader.loadPuntosCarga(puntosCargaDTO);
		log.debug("Finalizada carga desde el modelo de Open Charge Map");
	}	
	
	@Transactional
	@Scheduled(fixedRate = 300000)
	public void updateCargas() {
		log.debug("Comenzando actualización de cargas en estado CARGANDO");
		cargaService.updateCargas();
		log.debug("Finalizada la actualización de cargas");
	}
}
