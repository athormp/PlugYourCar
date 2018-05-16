package com.plugyourcar.backend.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.plugyourcar.backend.dto.PuntoCargaDTO;

/* Clase responsable de mantener métodos que se ejecutarán con una cierta frecuencia, como por ejempo la carga y actualización de los datos
 * de los puntos de carga que se obtendrán por llamada REST de Open Charge Map 
 */

@Component
public class ScheduledTasks {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	 
	@Scheduled(fixedRate = 60000)
	public void loadPOIFromOCM() {
		log.debug("Llamada REST");
		PuntoCargaDTO[] puntosCargaDTO = restTemplate.getForObject("http://api.openchargemap.io/v2/poi/?output=json&countrycode=ES&maxresults=1&compact=true&verbose=false&includecomments=false&opendata=true", PuntoCargaDTO[].class);
		log.debug(puntosCargaDTO.toString());
	}
	    
}
