package com.plugyourcar.backend.services;

import java.util.List;

import com.plugyourcar.backend.dto.CargaDetalleDTO;
import com.plugyourcar.backend.dto.CargaResumenDTO;

public interface CargaService {
	
	void iniciarCarga(Integer idConector, Boolean cargaConReserva);
	List<CargaResumenDTO> getCargas();
	CargaDetalleDTO getCarga(Integer idCarga);
	void updateCargas();
	CargaDetalleDTO finalizarCarga(Integer idCarga);
	
}
