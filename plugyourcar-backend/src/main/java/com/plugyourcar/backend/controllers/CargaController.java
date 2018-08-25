package com.plugyourcar.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.exceptions.SaldoInsuficienteException;
import com.plugyourcar.backend.services.CargaService;

@RestController
@RequestMapping("/carga")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @RequestMapping(method = RequestMethod.POST, value="{idConector}")
    public void crearCarga(@PathVariable("idConector") Integer idConector) throws SaldoInsuficienteException {
    	cargaService.iniciarCarga(idConector);
    }

}
