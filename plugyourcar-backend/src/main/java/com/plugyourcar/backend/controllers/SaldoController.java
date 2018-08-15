package com.plugyourcar.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.model.Saldo;
import com.plugyourcar.backend.services.SaldoService;

@RestController
@RequestMapping("/saldo")
public class SaldoController {
	
	@Autowired
	private SaldoService saldoService;
	
	@RequestMapping(value = "{userName}", method = RequestMethod.GET)
    public Saldo consultarSaldo(@PathVariable(value = "userName") String userName){
        return saldoService.consultarSaldo(userName);
    }
	
}
