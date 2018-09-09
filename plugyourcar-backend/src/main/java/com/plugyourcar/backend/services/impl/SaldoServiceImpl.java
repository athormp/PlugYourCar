package com.plugyourcar.backend.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.dto.SaldoDTO;
import com.plugyourcar.backend.exceptions.SaldoInsuficienteException;
import com.plugyourcar.backend.model.Saldo;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.SaldoRepository;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.SaldoService;

// Clase de servicio responsable de entregar el saldo diponible y gestionar la recarga

@Service
public class SaldoServiceImpl implements SaldoService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	private static final Logger log = LoggerFactory.getLogger(SaldoServiceImpl.class);

	// Método para la consulta de saldo
	public SaldoDTO consultarSaldo() {
		// Se recupera el usuario logado que envía la petición
		log.info("Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				+ " consultando su saldo");
		Usuario usuario = usuarioRepository
				.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		
		// Se recupera el objeto saldo asociado al usuario para 
		Saldo saldo = usuario.getSaldo();
		ModelMapper modelMapper = new ModelMapper();
		SaldoDTO saldoDTO = modelMapper.map(saldo, SaldoDTO.class);
		return saldoDTO;
	}
	
	// Método para la recarga de saldo
	public void cargarSaldo(SaldoDTO saldoDTO) {
		// Se recupera el saldo por el id
		Optional<Saldo> saldoOptional = saldoRepository.findById(saldoDTO.getId());
		StringBuilder errorMessage = new StringBuilder();
		if (!saldoOptional.isPresent() || saldoDTO.getCantidad() < 10 || saldoDTO.getCantidad() > 150) {
			log.info("No se pudo realizar la operación de recarga para el saldo con id: " + saldoDTO.getId());
			errorMessage.append("No se pudo realizar la operación de recarga, identificador de saldo o cantidad no permitida");
			throw new SaldoInsuficienteException(saldoDTO.getId().toString(), errorMessage);
		} else {
			Saldo saldo = saldoOptional.get();
			saldo.setCantidadDisponible(saldo.getCantidadDisponible() + saldoDTO.getCantidad()); 
			saldoRepository.saveAndFlush(saldo);	
		}
		
	}
}
