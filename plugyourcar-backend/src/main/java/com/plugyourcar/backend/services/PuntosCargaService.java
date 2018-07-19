package com.plugyourcar.backend.services;

import java.util.List;
import com.plugyourcar.backend.dto.PosicionDTO;
import com.plugyourcar.backend.dto.PuntoCargaDTO;

public interface PuntosCargaService {

	List<PuntoCargaDTO> obtenerPuntosCarga(PosicionDTO posicionDTO);
	
}