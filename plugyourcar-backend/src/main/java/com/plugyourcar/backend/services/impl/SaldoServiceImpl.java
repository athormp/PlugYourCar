package com.plugyourcar.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.plugyourcar.backend.model.Saldo;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.SaldoService;

public class SaldoServiceImpl implements SaldoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Saldo consultarSaldo(String username) {
		Usuario usuario = usuarioRepository.findByUserName(username);
		return usuario.getSaldo();
	}

}
