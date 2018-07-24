package com.plugyourcar.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.dto.PuntoCargaResumenDTO;
import com.plugyourcar.backend.services.PuntosCargaService;
import com.plugyourcar.backend.services.impl.PuntosCargaServiceImpl;

@RestController
@RequestMapping("/puntocarga")
public class PuntosCargaController {

    @Autowired
    private PuntosCargaService puntosCargaService;
    
    private static final Logger log = LoggerFactory.getLogger(PuntosCargaServiceImpl.class);

    @RequestMapping(method = RequestMethod.POST)
    public List<PuntoCargaResumenDTO> getPuntosCarga(@RequestBody PosicionDTO posicion) {
    	return puntosCargaService.obtenerPuntosCarga(posicion);
    }

}