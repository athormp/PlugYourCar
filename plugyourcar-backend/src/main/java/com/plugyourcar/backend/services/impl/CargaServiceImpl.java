package com.plugyourcar.backend.services.impl;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.exceptions.SaldoInsuficienteException;
import com.plugyourcar.backend.model.Carga;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EquipoSuministro;
import com.plugyourcar.backend.model.PuntoCarga;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.CargaRepository;
import com.plugyourcar.backend.repositories.ConectorRepository;
import com.plugyourcar.backend.repositories.EstadoConectorRepository;
import com.plugyourcar.backend.repositories.PuntoCargaRepository;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.CargaService;

// Clase de servicio responsable de generar nuevas cargas para un usuario y un conector concreto

@Service
public class CargaServiceImpl implements CargaService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConectorRepository conectorRepository;

	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private PuntoCargaRepository puntoCargaRepository;

	@Autowired
	private EstadoConectorRepository estadoConectorRepository;

	private static final Logger log = LoggerFactory.getLogger(CargaServiceImpl.class);

	// Método para iniciar una nueva carga
	@Transactional
	public void iniciarCarga(Integer idConector) throws SaldoInsuficienteException {

		StringBuilder errorMessage = new StringBuilder();
		// Se recupera el usuario logado que envía la petición
		log.info("Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				+ " solicitando iniciar una carga para el conector" + idConector);
		Usuario usuario = usuarioRepository
				.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		// Si el saldo del usuario es inferior a 20 euros se lanza la excepción
		// de saldo insuficiente
		if (usuario.getSaldo().getCantidadDisponible() < 20) {
			log.info("Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
					+ " no tiene saldo suficiente para iniciar la carga");
			errorMessage.append("Saldo insuficiente. Debe disponer de un saldo mínimo de 20 euros"
					+ " para poder realizar esta operación");
			throw new SaldoInsuficienteException(usuario.getSaldo().getCantidadDisponible().toString(), errorMessage);
		}

		Conector conector = conectorRepository.getOne(idConector);

		// Si el conector está en un estado distinto de 1 se lanza la excepción
		// de que no está libre
		if (conector.getEstadoConector().getId() != 1) {
			log.info("Intentando iniciar una carga sobre el conector con id: " + conector.getEstadoConector().getId() + " no libre o no integrado");
			errorMessage.append("El conector no se encuentra está en estado libre o corresponde"
					+ " a un operador que no admite iniciar cargas desde la aplicación");
			throw new SaldoInsuficienteException(conector.getId().toString(), errorMessage);
		}

		// Se cambia el estado del conector a CARGANDO, identificador 2
		conector.setEstadoConector(estadoConectorRepository.getOne(2));
		log.info("Actualizado estado del conector: " + conector.getId() + " a CARGANDO");

		// Se genera el nuevo objeto Carga
		Carga carga = new Carga();
		carga.setUsuario(usuario);
		carga.setConector(conector);
		carga.setHoraInicio(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));

		cargaRepository.saveAndFlush(carga);

		// Se recalcula el estado de ocupación del punto de carga
		PuntoCarga puntoCarga = puntoCargaRepository.getOne(conector.getEquipoSuministro().getPuntoCarga().getId());
		int numConectores = 0;
		int numConectoresOcupados = 0;
		for (EquipoSuministro equipoSuministro : puntoCarga.getEquiposSuministro()) {
			numConectores += equipoSuministro.getConectores().size();
			for (Conector conectorES : equipoSuministro.getConectores()) {
				if (conectorES.getEstadoConector().getId() != 1)
					numConectoresOcupados += 1;
			}
		}
		float estadoOcupacion = ((float) numConectoresOcupados / (float) numConectores) * 100;
		int estadoOcupacionFinal = Math.round(estadoOcupacion);
		puntoCarga.setEstadoOcupacion(estadoOcupacionFinal);
		log.info("Actualizado estado de ocupación del punto de carga con id " + puntoCarga.getId() + ": "
				+ estadoOcupacionFinal + "%");
		puntoCargaRepository.saveAndFlush(puntoCarga);

	}

}
