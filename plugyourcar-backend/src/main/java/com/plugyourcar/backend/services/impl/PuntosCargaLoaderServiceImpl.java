package com.plugyourcar.backend.services.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.dto.ConectorDTO;
import com.plugyourcar.backend.dto.LocalizacionDTO;
import com.plugyourcar.backend.dto.PuntoCargaDTO;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EquipoSuministro;
import com.plugyourcar.backend.model.Localizacion;
import com.plugyourcar.backend.model.Operador;
import com.plugyourcar.backend.model.PuntoCarga;
import com.plugyourcar.backend.model.TipoCargador;
import com.plugyourcar.backend.model.TipoConector;
import com.plugyourcar.backend.model.TipoCorriente;
import com.plugyourcar.backend.model.TipoEstado;
import com.plugyourcar.backend.model.TipoUso;
import com.plugyourcar.backend.repositories.ConectorRepository;
import com.plugyourcar.backend.repositories.LocalizacionRepository;
import com.plugyourcar.backend.repositories.OperadorRepository;
import com.plugyourcar.backend.repositories.PuntoCargaRepository;
import com.plugyourcar.backend.repositories.TipoCargadorRepository;
import com.plugyourcar.backend.repositories.TipoConectorRepository;
import com.plugyourcar.backend.repositories.TipoCorrienteRepository;
import com.plugyourcar.backend.repositories.TipoEstadoRepository;
import com.plugyourcar.backend.repositories.TipoUsoRepository;
import com.plugyourcar.backend.services.PuntosCargaLoaderService;
import com.plugyourcar.backend.utils.Utils;


/* Clase de servicio responsable de cargar los datos abiertos y referentes a los puntos de carga nacionales 
 */
@Service
public class PuntosCargaLoaderServiceImpl implements PuntosCargaLoaderService{
	
	@Autowired
	private PuntoCargaRepository puntoCargaRepository;
	
	@Autowired
	private OperadorRepository operadorRepository;
	
	@Autowired
	private TipoUsoRepository tipoUsoRepository;
	
	@Autowired
	private TipoCargadorRepository tipoCargadorRepository;
	
	@Autowired
	private TipoConectorRepository tipoConectorRepository;
	
	@Autowired
	private TipoCorrienteRepository tipoCorrienteRepository;
	
	@Autowired
	private LocalizacionRepository localizacionRepository;
	
	@Autowired
	private ConectorRepository conectorRepository;
	
	@Autowired
	private TipoEstadoRepository tipoEstadoRepository;

	private static final Logger log = LoggerFactory.getLogger(PuntosCargaLoaderServiceImpl.class);

	/* Método que recibe el array de puntos de carga en los ibjetos DTO que se obtienen por llamada REST y los transforma a las
	 * clases de dominio y carga en la base de datos
	 */
	public void loadPuntosCarga(PuntoCargaDTO[] puntosCargaDTO) { 
		for (PuntoCargaDTO puntoCargaDTO : puntosCargaDTO) {
			try {
				Timestamp timestamp = Utils.dateFormatterA(puntoCargaDTO.getUltimaFechaActualizacion());
				/* Se verifica si existe el punto de carga en base de datos y si la fecha de última actualización ha variado con respecto a la
				 * anterior carga para determinar si se hace un UPDATE del objeto o no
				 */
				if (puntoCargaRepository.findById(puntoCargaDTO.getId()).isPresent()) {
					PuntoCarga puntoCarga = puntoCargaRepository.getOne(puntoCargaDTO.getId());
					Timestamp timestamp2 = puntoCarga.getUltimaFechaActualizacion();
					if (timestamp.after(timestamp2)) {
						loadPuntoCarga(puntoCargaDTO, puntoCarga);
					}
				// Si el objeto es nulo se hace un INSERT
				} else {
					PuntoCarga puntoCargaNuevo = new PuntoCarga();
					loadPuntoCarga(puntoCargaDTO, puntoCargaNuevo);
				}
			} catch (ParseException dfe) {
				log.debug("Fecha mal formateada: " + puntoCargaDTO.getUltimaFechaActualizacion());
			}
		}
	}
	
	/* Este método se encarga de transformar un punto de carga y cargarlo en base de datos
	 */
	@Transactional
	private void loadPuntoCarga(PuntoCargaDTO puntoCargaDTO, PuntoCarga puntoCarga) throws DataException, ParseException {
		try {
			// Si el id del punto de carga es nulo se inicia el INSERT, por ello se mete el ID y varios datos que no deberían variar nunca
			log.info("Punto de carga ID : " + puntoCargaDTO.getId() + " - Punto de Carga UUID: " + puntoCargaDTO.getUuid());
			if (puntoCarga.getId() == null) {
				puntoCarga.setId(puntoCargaDTO.getId());
				puntoCarga.setUuid(puntoCargaDTO.getUuid());
				puntoCarga.setReferenciaOperador(puntoCargaDTO.getReferenciaOperador());
				Timestamp timestamp = Utils.dateFormatterA(puntoCargaDTO.getFechaCreacion());
				puntoCarga.setFechaCreacion(timestamp);
			}
			puntoCarga.setCosteUso(puntoCargaDTO.getCosteUso());
			puntoCarga.setNumeroPuntos(puntoCargaDTO.getNumeroPuntos());
			
			if (puntoCargaDTO.getLocalizacioDTO() != null) {
				// Si ya existe la localización en base de datos se obtiene la misma para pasarla como parámetro y hacer un UPDATE
				if (localizacionRepository.findById(puntoCargaDTO.getLocalizacioDTO().getId()).isPresent()) {
					Localizacion localizacion = localizacionRepository.getOne(puntoCargaDTO.getLocalizacioDTO().getId());
					puntoCarga.setLocalizacion(loadLocalizacion(puntoCargaDTO.getLocalizacioDTO(), puntoCargaDTO, localizacion));
				} else {
					Localizacion localizacionNueva = new Localizacion();
					puntoCarga.setLocalizacion(loadLocalizacion(puntoCargaDTO.getLocalizacioDTO(), puntoCargaDTO, localizacionNueva));
				}
				log.info("Localizacion ID " + puntoCargaDTO.getLocalizacioDTO().getNombre());
			}
		
			if (puntoCargaDTO.getOperador() != null) {
				Operador operador = operadorRepository.getOne(puntoCargaDTO.getOperador());
				puntoCarga.setOperador(operador);
			}
		
			if (puntoCargaDTO.getTipoUso() != null) {
				TipoUso tipoUso = tipoUsoRepository.getOne(puntoCargaDTO.getTipoUso());
				puntoCarga.setTipoUso(tipoUso);
			}
		
			List<EquipoSuministro> equiposSuministroNuevos = new ArrayList<EquipoSuministro>();
			if (puntoCargaDTO.getConectoresDTO() != null && !puntoCargaDTO.getConectoresDTO().isEmpty()) {
				log.info("ConectoresDTO: " + puntoCargaDTO.getConectoresDTO().size());
				
				if (puntoCarga.getEquiposSuministro() != null && !puntoCarga.getEquiposSuministro().isEmpty()) {
					puntoCarga.setEquiposSuministro(loadEquiposSuministroConectores(puntoCarga, puntoCargaDTO.getConectoresDTO(), puntoCarga.getEquiposSuministro()));
				} else {
					puntoCarga.setEquiposSuministro(loadEquiposSuministroConectores(puntoCarga, puntoCargaDTO.getConectoresDTO(), equiposSuministroNuevos));
				}
				
			}
			
			puntoCarga.setUltimaFechaActualizacion(Utils.dateFormatterA(puntoCargaDTO.getUltimaFechaActualizacion()));
			
			if (puntoCargaDTO.getTipoEstadoDTO() != null) {
				TipoEstado tipoEstado = tipoEstadoRepository.getOne(puntoCargaDTO.getTipoEstadoDTO());
				puntoCarga.setTipoEstado(tipoEstado);
			}
			
			puntoCargaRepository.saveAndFlush(puntoCarga);
		
		} catch (DataException de) {
			log.debug("Error al cargar el punto de carga con ID: " + puntoCargaDTO.getId());
			de.getMessage().toString();
		
		} catch (Exception e) {
			log.debug("Error al cargar el punto de carga con ID: " + puntoCargaDTO.getId());
			e.getMessage().toString();
		}
	}
	
	/*
	 * Se encarga de transformar y cargar la localización en base de datos
	 */
	@Transactional
	private Localizacion loadLocalizacion(LocalizacionDTO localizacionDTO, PuntoCargaDTO puntoCargaDTO, Localizacion localizacion) {
		if (localizacion.getId() == null) {
			localizacion.setId(puntoCargaDTO.getLocalizacioDTO().getId());
		}
		localizacion.setNombre(puntoCargaDTO.getLocalizacioDTO().getNombre());
		localizacion.setDireccion(puntoCargaDTO.getLocalizacioDTO().getDireccion());
		localizacion.setLocalidad(puntoCargaDTO.getLocalizacioDTO().getLocalidad());
		localizacion.setProvincia(puntoCargaDTO.getLocalizacioDTO().getProvincia());
		localizacion.setCodigoPostal(puntoCargaDTO.getLocalizacioDTO().getCodigoPostal());
		localizacion.setTelefonoContacto(puntoCargaDTO.getLocalizacioDTO().getTelefonoContacto());
		localizacion.setLatitud(puntoCargaDTO.getLocalizacioDTO().getLatitud());
		localizacion.setLongitud(puntoCargaDTO.getLocalizacioDTO().getLongitud());
		localizacionRepository.save(localizacion);
		return localizacion;
	}
	
	@Transactional
	private List<EquipoSuministro> loadEquiposSuministroConectores(PuntoCarga puntoCarga, List<ConectorDTO> conectoresDTO, List<EquipoSuministro> equiposSuministro) {
		for (ConectorDTO conectorDTO : conectoresDTO) {
			int i = 0;
			log.info("Equipo de suministro ID: " + conectorDTO.getId());
			if (equiposSuministro.get(i) == null) {
				EquipoSuministro equipoSuministro = new EquipoSuministro();
				equipoSuministro.setId(conectorDTO.getId());
				loadParcialEquipoSuministro(equipoSuministro, conectorDTO);
				equiposSuministro.add(equipoSuministro);
			} else {
				loadParcialEquipoSuministro(equiposSuministro.get(i), conectorDTO);
				equiposSuministro.get(i).setPuntoCarga(puntoCarga);
			}
			i++;
		}
		return equiposSuministro;
	}
	
	@Transactional
	private void loadParcialEquipoSuministro(EquipoSuministro equipoSuministro, ConectorDTO conectorDTO) {
		equipoSuministro.setAmperaje(conectorDTO.getAmperaje());
		equipoSuministro.setPotencia(conectorDTO.getPotencia());
		equipoSuministro.setVoltaje(conectorDTO.getVoltaje());
		if (conectorDTO.getTipoCargador() != null) {
			TipoCargador tipoCargador = tipoCargadorRepository.getOne(conectorDTO.getTipoCargador());
			equipoSuministro.setTipoCargador(tipoCargador);
		}
		if (conectorDTO.getTipoConector() != null) {
			TipoConector tipoConector = tipoConectorRepository.getOne(conectorDTO.getTipoConector());
			equipoSuministro.setTipoConector(tipoConector);
		}
		if (conectorDTO.getTipoCorriente() != null) {
			TipoCorriente tipoCorriente = tipoCorrienteRepository.getOne(conectorDTO.getTipoCorriente());
			equipoSuministro.setTipoCorriente(tipoCorriente);
		}
		List<Conector> conectores = new ArrayList<Conector>();
		if (conectorDTO.getQuantity() != null) {
			for (int i = 0; i < conectorDTO.getQuantity(); i++) {
				if (conectorRepository.findByIdReferenciaAndEquipoSuministro(i + 1, equipoSuministro) == null) {
					Conector conector = new Conector();
					conector.setIdReferencia(i+1);
					conector.setEquipoSuministro(equipoSuministro);
					conectores.add(conector);
				}
			}
		}
		equipoSuministro.setConectores(conectores);
		
	}
}