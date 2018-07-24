package com.plugyourcar.backend.services;

import java.util.List;

import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.dto.PuntoCargaResumenDTO;

public interface PuntosCargaService {

	List<PuntoCargaResumenDTO> obtenerPuntosCarga(PosicionDTO posicionDTO);
	
}