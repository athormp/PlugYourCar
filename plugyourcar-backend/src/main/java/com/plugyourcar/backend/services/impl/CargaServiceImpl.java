package com.plugyourcar.backend.services.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.dto.CargaDetalleDTO;
import com.plugyourcar.backend.dto.CargaResumenDTO;
import com.plugyourcar.backend.dto.FechasReservaDTO;
import com.plugyourcar.backend.exceptions.CargaException;
import com.plugyourcar.backend.exceptions.ConectorException;
import com.plugyourcar.backend.exceptions.OperacionNoAdmitidaException;
import com.plugyourcar.backend.exceptions.SaldoInsuficienteException;
import com.plugyourcar.backend.model.Carga;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EquipoSuministro;
import com.plugyourcar.backend.model.EstadoCarga;
import com.plugyourcar.backend.model.PuntoCarga;
import com.plugyourcar.backend.model.Saldo;
import com.plugyourcar.backend.model.Tarifa;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.CargaRepository;
import com.plugyourcar.backend.repositories.ConectorRepository;
import com.plugyourcar.backend.repositories.EstadoCargaRepository;
import com.plugyourcar.backend.repositories.EstadoConectorRepository;
import com.plugyourcar.backend.repositories.PuntoCargaRepository;
import com.plugyourcar.backend.repositories.SaldoRepository;
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

	@Autowired
	private EstadoCargaRepository estadoCargaRepository;
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	private PropertyMap<Carga, CargaResumenDTO> ToCargaResumenMapping = new PropertyMap<Carga, CargaResumenDTO>() {
		protected void configure() {
			map().setNombrePuntoCarga(
					source.getConector().getEquipoSuministro().getPuntoCarga().getLocalizacion().getNombre());
			map().setOperador(source.getConector().getEquipoSuministro().getPuntoCarga().getOperador().getNombre());
			map().setEstadoCarga(source.getEstadoCarga().getDescripcion());
		}
	};
	
	private PropertyMap<Carga, CargaDetalleDTO> ToCargaDetalleMapping = new PropertyMap<Carga, CargaDetalleDTO>() {
		protected void configure() {
			map().setNombrePuntoCarga(
					source.getConector().getEquipoSuministro().getPuntoCarga().getLocalizacion().getNombre());
			map().setOperador(source.getConector().getEquipoSuministro().getPuntoCarga().getOperador().getNombre());
			map().setTipoConector(source.getConector().getEquipoSuministro().getTipoConector());
		}
	};

	private static final Logger log = LoggerFactory.getLogger(CargaServiceImpl.class);

	// Método para obtener todas las cargas de un usuario, tanto de reserva
	// previa como directas
	public List<CargaResumenDTO> getCargas() {

		// Se recupera el usuario logado que envía la petición
		log.info("Usuario con id " + SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal().toString()
				+ " recuperando todas sus cargas");
		Usuario usuario = usuarioRepository
				.findByUserName(SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal().toString());
		List<Carga> cargas = cargaRepository.findAllByUsuario(usuario);
		List<CargaResumenDTO> cargasResumenDTO = new ArrayList<CargaResumenDTO>();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(ToCargaResumenMapping);
		for (Carga carga : cargas) {
			CargaResumenDTO cargaResumenDTO = modelMapper.map(carga, CargaResumenDTO.class);
			cargasResumenDTO.add(cargaResumenDTO);
		}
		return cargasResumenDTO;
	}
	
	// Método para obtener todas las reservas de un conector en los 7 días siguientes a la hora de la petición
	
	public List<CargaResumenDTO> getReservas(Integer idConector) {

		// Se recupera el conector
		Conector conector = conectorRepository.getOne(idConector);
		List<Carga> reservas = cargaRepository.findAllByConector(conector);
		List<CargaResumenDTO> cargasResumenDTO = new ArrayList<CargaResumenDTO>();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(ToCargaResumenMapping);
		for (Carga carga : reservas) {
			CargaResumenDTO cargaResumenDTO = modelMapper.map(carga, CargaResumenDTO.class);
			cargasResumenDTO.add(cargaResumenDTO);
		}
		return cargasResumenDTO;
	}

	// Método para obtener el detalle de una carga iniciada
	public CargaDetalleDTO getCarga(Integer idCarga) {

		// Se intenta recuperar la carga por el id pasado como parámetro en la URL
		Optional<Carga> cargaOpcional = cargaRepository.findById(idCarga);
		Carga carga = new Carga();
		StringBuilder errorMessage = new StringBuilder();
		
		// Se comprueba si existe la carga por el id
		if (cargaOpcional.isPresent()) {
			carga = cargaOpcional.get();
		} else {
			log.info("No existe ninguna carga con id: " + idCarga);
			errorMessage.append("No existe ninguna carga con el id solicitado: " + idCarga);
			throw new CargaException(carga.getId().toString(), errorMessage);
		}
		
		// Se mapea la carga al objeto que será transferido al cliente
		ModelMapper modelMapper = new ModelMapper();
	    modelMapper.addMappings(ToCargaDetalleMapping);
	    CargaDetalleDTO cargaDetalleDTO = modelMapper.map(carga, CargaDetalleDTO.class);
	    cargaDetalleDTO.setReferencia(carga.getConector().getEquipoSuministro().getPuntoCarga().getId() + "/" + carga.getConector().getEquipoSuministro().getId() + "/" + carga.getConector().getId() + "/" + carga.getConector().getIdReferencia());
		return cargaDetalleDTO;
	}

	// Método para iniciar una nueva carga o reserva, depende del tipo de
	// operación
	// marcada en la request
	@Transactional
	public void iniciarCarga(Integer idConector, Boolean cargaConReserva, FechasReservaDTO fechasReserva) throws SaldoInsuficienteException {

		Carga carga = new Carga();
		StringBuilder errorMessage = new StringBuilder();
		// Se recupera el usuario logado que envía la petición
		log.info("Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				+ " solicitando iniciar una carga para el conector" + idConector);
		Usuario usuario = usuarioRepository
				.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		// Si el saldo del usuario es inferior a 20 euros se lanza la excepción
		// de saldo insuficiente
		if (usuario.getSaldo().getCantidadDisponible() < 20) {
			log.info(
					"Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
							+ " no tiene saldo suficiente para iniciar la carga");
			errorMessage.append("Saldo insuficiente. Debe disponer de un saldo mínimo de 20 euros"
					+ " para poder realizar esta operación");
			throw new SaldoInsuficienteException(usuario.getSaldo().getCantidadDisponible().toString(), errorMessage);
		}

		Conector conector = conectorRepository.getOne(idConector);

		// Si el conector está en un estado distinto de 1 se lanza la excepción
		// de que no está libre
		if (conector.getEstadoConector().getId() != 1) {
			log.info("Intentando iniciar una carga sobre el conector con id: " + conector.getEstadoConector().getId()
					+ " no libre o no integrado");
			errorMessage.append("El conector no está en estado libre o corresponde"
					+ " a un operador que no admite iniciar cargas desde la aplicación");
			throw new ConectorException(conector.getId().toString(), errorMessage);
		}
		// Se asigna usuario a la carga
		carga.setUsuario(usuario);
		// Si la petición es para iniciar una carga sin reserva se
		// cambia el estado del conector a CARGANDO, identificador 2
		if (!cargaConReserva) {
			// Se comprueba que el equipo admite inicio directo de carga
			if (conector.getEquipoSuministro().getAdmiteReserva() != null
					&& conector.getEquipoSuministro().getAdmiteReserva()) {
				log.info("Intentando iniciar una carga sobre el conector con id: "
						+ conector.getEstadoConector().getId());
				errorMessage.append(
						"El conector no admite inicio directo de cargas, previamente debe realizarse una reserva");
				throw new OperacionNoAdmitidaException(conector.getId().toString(), errorMessage);
			}
			conector.setEstadoConector(estadoConectorRepository.getOne(2));
			log.info("Actualizado estado del conector: " + conector.getId() + " a CARGANDO");

			// Se asigna conector, hora_inicio, 
			// y se cambia el estado a CARGANDO
			carga.setConector(conector);
			carga.setHoraInicio(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			carga.setCargaConReserva(cargaConReserva);
			carga.setEstadoCarga(estadoCargaRepository.getOne(2));
			cargaRepository.saveAndFlush(carga);
			recalcularEstadoOcupacion(conector);
		} else {
			// Si la petición es para iniciar un proceso de reserva se mantiene el conector libre, 
			// se aceptan reservas con mínimo 15 minutos de antelación a la fecha actual.
			// Se comprueba que el equipo admite inicio directo de carga
			if (conector.getEquipoSuministro().getAdmiteReserva() != null
				&& !conector.getEquipoSuministro().getAdmiteReserva()) {
				log.info("Intentando iniciar una carga sobre el conector con id: "
					+ conector.getEstadoConector().getId());
				errorMessage.append("El conector no funciona por sistema de reservas");
				throw new OperacionNoAdmitidaException(conector.getId().toString(), errorMessage);
			}
			// Se valida que la fecha de inicio es 15 minutos anterior a ahora y que la fecha de 
			// fin no es inferior a la de inicio
			LocalDateTime inicio = LocalDateTime.parse(fechasReserva.getFechaInicio().replace("Z", ""));
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(inicio, now);
		    long diff = duration.getSeconds();
			if (diff > 900 || LocalDateTime.parse(fechasReserva.getFechaFin().replace("Z", ""))
					.isBefore(LocalDateTime.parse(fechasReserva.getFechaInicio().replace("Z", "")))) {
				// Se asigna conector, hora inicio reserva y hora fin reserva
				log.info("Las fechas de solicitud de la reserva sobre el conector: " + idConector + " no son correctas");
				errorMessage.append("Las fechas de solicitud de la reserva sobre el conector: " + idConector + " no son correctas");
				throw new CargaException(conector.getId().toString(), errorMessage);
			}
			carga.setConector(conector);
			carga.setCargaConReserva(cargaConReserva);
			carga.setHoraInicioReserva(Timestamp.from(Instant.parse(fechasReserva.getFechaInicio())));
			carga.setHoraFinReserva(Timestamp.from(Instant.parse(fechasReserva.getFechaFin())));
			carga.setEstadoCarga(estadoCargaRepository.getOne(1));
			cargaRepository.saveAndFlush(carga);
		}
	}
	
	// Método para finalizar una carga desde la aplicación. El usuario en este momento 
	// es cuando desconecta de la batería de su vehículo el conector, se emite la factura final
	// con los cargos adicionales que correspondan en su caso
	@Transactional
	public CargaDetalleDTO finalizarCarga(Integer idCarga) {
		
		StringBuilder errorMessage = new StringBuilder();
		
		// Se recupera el usuario logado que envía la petición
		log.info("Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				+ " solicitando finalizar la carga con id " + idCarga);
		Usuario usuario = usuarioRepository
				.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		// Se intenta recuperar la carga por el id pasado como parámetro en la URL
		Optional<Carga> cargaOpcional = cargaRepository.findById(idCarga);
		Carga carga = new Carga();
				
		// Se comprueba si existe la carga por el id
		if (cargaOpcional.isPresent()) {
			carga = cargaOpcional.get();
		} else {
			log.info("No existe ninguna carga con id: " + idCarga);
			errorMessage.append("No existe ninguna carga con el id solicitado: " + idCarga);
			throw new CargaException(carga.getId().toString(), errorMessage);
		}
		Conector conector = carga.getConector();
		conector.setEstadoConector(estadoConectorRepository.getOne(3));
		
		// Si la carga aún no ha finalizado se llama al método que actualiza los datos de
		// de la carga en este estado
		if (carga.getEstadoCarga().getId() == 2) {
			actualizarDatosCargando(carga, conector);
		// Si la carga ya ha finalizado se llama al método que actualiza los datos de
		// de la carga en este estado
		} else if (carga.getEstadoCarga().getId() == 3) {
			actualizarDatosFinalizada(carga, conector);
		}
		carga.setEstadoCarga(estadoCargaRepository.getOne(4));
		
		// Se debe descontar el coste del saldo al usuario
		if (usuario.getSaldo().getCantidadDisponible() < carga.getCargoFactura()) {
			log.info(
					"Usuario con id " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
							+ " no tiene saldo suficiente para finalizar la carga");
			errorMessage.append("Saldo insuficiente. Debe disponer de un saldo mínimo de " + (carga.getCargoFactura() + 20) + " euros"
					+ " para poder cerrar esta operación");
			throw new SaldoInsuficienteException(usuario.getSaldo().getCantidadDisponible().toString(), errorMessage);
		}
		// Se mapea la carga al objeto que será transferido al cliente
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(ToCargaDetalleMapping);
		CargaDetalleDTO cargaDetalleDTO = modelMapper.map(carga, CargaDetalleDTO.class);
		cargaDetalleDTO.setReferencia(carga.getConector().getEquipoSuministro().getPuntoCarga().getId()
				+ "/" + carga.getConector().getEquipoSuministro().getId()
				+ "/" + carga.getConector().getId()
				+ "/" + carga.getConector().getIdReferencia());
		Saldo saldo = usuario.getSaldo();
		saldo.setCantidadDisponible(saldo.getCantidadDisponible() - carga.getCargoFactura());
		cargaRepository.saveAndFlush(carga);
		saldoRepository.saveAndFlush(saldo);
		recalcularEstadoOcupacion(conector);
		return cargaDetalleDTO;
	}
	
	// Método llamado por un scheduler que actualizará todas las cargas en estado 2 (CARGANDO)
	// o en estado 3 (FINALIZADA, a falta de desconectar por el usuario. Se ejecutará
	// con una frecuencia de 5 minutos
	@Transactional
	public void updateCargas() {
		// Se recuperan todas las cargas en estado 2 (CARGANDO)
		List<EstadoCarga> estadosCarga = new ArrayList<EstadoCarga>();
		estadosCarga.add(estadoCargaRepository.getOne(2));
		estadosCarga.add(estadoCargaRepository.getOne(3));
		List<Carga> cargas = cargaRepository.findAllByEstadoCargaIn(estadosCarga);
		
		// Se recorre el array de cargas
		for (Carga carga : cargas) {
			// Se recupera el conector asociado a la recarga para conocer su
			// potencia y
			// la tarifa que tiene asociada su equipo de suministro. Si no dispone
			// del dato de potencia
			// se buscará el tipo de cargador, y si es de carga semi-rápida se le
			// asignará una potencia
			// ideal de 11kw. Si es de carga rápida se le asignará una potencia
			// ideal de 50kw. Si el equipo
			// no dispone de ninguno de los dos datos se le asignará una potencia
			// ideal de 17kw.
			Conector conector = carga.getConector();
			
			// Si la carga está en estado 2 se llama al método de actualización
			// para el estado 2
			if (carga.getEstadoCarga().getId() == 2) {
				actualizarDatosCargando(carga, conector);
			// Si la carga está en estado 3 se llama al método de
			// actualización para el estado 3
			} else if (carga.getEstadoCarga().getId() == 3) {
				actualizarDatosFinalizada(carga, conector);
			}
		    cargaRepository.saveAndFlush(carga);
		}
	}

	private void recalcularEstadoOcupacion(Conector conector) {
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
	
	// Método para actualizar el coste y porcentaje de avance da las cargas en estado 2
	private void actualizarDatosCargando(Carga carga, Conector conector) {
		// se intenta recuperar la potencia del equipo de suministro
		double potencia;
		if (conector.getEquipoSuministro().getPotencia() != null) {
			potencia = conector.getEquipoSuministro().getPotencia();
		// si no hay dato de potencia se asigna el valor por tipo de cargador
		} else if (conector.getEquipoSuministro().getTipoCargador() != null) {
			if (conector.getEquipoSuministro().getTipoCargador().getCargaRapida()) {
				potencia = 50.0;
			} else {
				potencia = 11.0;
			}
		// si no hay ninguno de los datos se signa una potencia de 17
		} else {
			potencia = 17.0;
		}
		// Se considera que el vehículo llega con la batería al 10% de carga y que
		// se recargará hasta el 80%. La batería tiene una capacidad de 60kwh, por tanto
		// para todos los casos se recargarán 42kwh. Con estos datos se puede calcuar el
		// tiempo requerido estimado para la recarga
		long tiempoRecarga = (long)(((42.0 / potencia) / 0.9)*60*60);
		LocalDateTime inicio = carga.getHoraInicio().toLocalDateTime();
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(inicio, now);
	    long diff = duration.getSeconds();
	    double porcentaje;
	    double coste;
	    ZoneId zoneId = ZoneId.of("Europe/Brussels");
	    if (diff > tiempoRecarga) {
	    	porcentaje = 80;
	    	Timestamp fin = Timestamp.from(inicio.plusSeconds(tiempoRecarga).atZone(zoneId).toInstant());
	    	carga.setHoraFin(fin);
	    	carga.setEstadoCarga(estadoCargaRepository.getOne(3));
	    	coste = calcularTarifa(carga.getConector().getEquipoSuministro().getTarifa(), 42.0, null);
	    	carga.setPorcentaje(porcentaje);
	    	carga.setCargoFactura(coste);
	    } else {
	    	porcentaje = (double)diff / tiempoRecarga;
	    	porcentaje = (((double)diff / tiempoRecarga) * 100 * 80)/100;
	    	coste = calcularTarifa(carga.getConector().getEquipoSuministro().getTarifa(), (diff/3600)*potencia, null);
	    	carga.setPorcentaje(porcentaje);
	    	carga.setCargoFactura(coste);
	    }
	}
	
	private void actualizarDatosFinalizada(Carga carga, Conector conector) {
		LocalDateTime fin = carga.getHoraFin().toLocalDateTime();
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(fin, now);
		ZoneId zoneId = ZoneId.of("Europe/Brussels");
	    long diff = duration.getSeconds();
	    double coste = calcularTarifa(carga.getConector().getEquipoSuministro().getTarifa(), 42.0, diff/3600);
	    carga.setCargoFactura(coste);
	    carga.setHoraDesconexion(Timestamp.from(now.atZone(zoneId).toInstant()));
	}
	
	private double calcularTarifa(Tarifa tarifa, Double kwhCargados, Long tiempoExcedido) {
		double coste = 0.0;
		coste = tarifa.getPrecioKwh() * kwhCargados;
		if (tarifa.getPrecioServicioMinimo() != null && coste < tarifa.getPrecioServicioMinimo()) {
			coste = tarifa.getPrecioServicioMinimo();
		}
		if (tiempoExcedido != null && tarifa.getPrecioServicioAdicional() != null) {
			coste = coste + (tiempoExcedido * tarifa.getPrecioServicioAdicional());
		}
		return coste;
	}

}
