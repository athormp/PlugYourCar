package com.plugyourcar.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.dto.PuntoCargaDetalleDTO;
import com.plugyourcar.backend.dto.PuntoCargaResumenDTO;
import com.plugyourcar.backend.services.PuntosCargaService;

@RestController
@RequestMapping("/puntocarga")
public class PuntosCargaController {

    @Autowired
    private PuntosCargaService puntosCargaService;

    @RequestMapping(method = RequestMethod.POST)
    public List<PuntoCargaResumenDTO> getPuntosCarga(@RequestBody PosicionDTO posicion) {
    	return puntosCargaService.obtenerPuntosCarga(posicion);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="{id}")
    public PuntoCargaDetalleDTO getPuntoCarga(@PathVariable("id") Integer id) {
    	return puntosCargaService.obtenerDetallePuntoCarga(id);
    }

}