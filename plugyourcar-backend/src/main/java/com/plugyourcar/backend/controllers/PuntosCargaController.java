package com.plugyourcar.backend.controllers;

import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.services.PuntosCargaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/puntosCarga")
public class PuntosCargaController {

    @Autowired
    private PuntosCargaService puntosCargaService;

    @RequestMapping(method = RequestMethod.GET)
    public void getPuntosCarga(@RequestBody PosicionDTO posicion) {
        puntosCargaService.obtenerPuntosCarga(posicion);
    }

}