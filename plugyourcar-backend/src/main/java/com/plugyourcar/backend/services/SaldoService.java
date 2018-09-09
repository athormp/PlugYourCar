package com.plugyourcar.backend.services;

import com.plugyourcar.backend.dto.SaldoDTO;

public interface SaldoService {

	SaldoDTO consultarSaldo();
	void cargarSaldo(SaldoDTO saldo);
	
}
