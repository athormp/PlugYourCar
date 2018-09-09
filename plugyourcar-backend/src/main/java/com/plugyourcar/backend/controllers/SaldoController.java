package com.plugyourcar.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.dto.SaldoDTO;
import com.plugyourcar.backend.services.SaldoService;

@RestController
@RequestMapping("/saldo")
public class SaldoController {
	
	@Autowired
	private SaldoService saldoService;
	
	@RequestMapping(method = RequestMethod.GET)
		public SaldoDTO getSaldo() {
			return saldoService.consultarSaldo();
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	public void cargarSaldo(@RequestBody SaldoDTO saldoDTO) {
		saldoService.cargarSaldo(saldoDTO);
}
	
}
