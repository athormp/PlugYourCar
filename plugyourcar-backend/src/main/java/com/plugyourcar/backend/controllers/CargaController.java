package com.plugyourcar.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.dto.CargaDetalleDTO;
import com.plugyourcar.backend.dto.CargaResumenDTO;
import com.plugyourcar.backend.exceptions.CargaException;
import com.plugyourcar.backend.exceptions.OperacionNoAdmitidaException;
import com.plugyourcar.backend.exceptions.SaldoInsuficienteException;
import com.plugyourcar.backend.services.CargaService;

@RestController
@RequestMapping("/carga")
public class CargaController {

    @Autowired
    private CargaService cargaService;
    
    @RequestMapping(method = RequestMethod.GET, value="{idCarga}")
    public CargaDetalleDTO getCarga(@PathVariable("idCarga") Integer idCarga)  
    		throws CargaException {
    	return cargaService.getCarga(idCarga);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<CargaResumenDTO> getCargas() {
    	return cargaService.getCargas();
    }

	@RequestMapping(method = RequestMethod.POST, value = "{idConector}/{cargaConReserva}")
	public void crearCarga(@PathVariable("idConector") Integer idConector,
						   @PathVariable("cargaConReserva") Boolean cargaConReserva)
						   throws SaldoInsuficienteException, OperacionNoAdmitidaException {
		cargaService.iniciarCarga(idConector, cargaConReserva);
	}
    
    @RequestMapping(method = RequestMethod.PUT, value="{idCarga}")
    public CargaDetalleDTO finalizarCarga(@PathVariable("idCarga") Integer idCarga) 
    		throws CargaException {
    	return cargaService.finalizarCarga(idCarga);
    }

}
