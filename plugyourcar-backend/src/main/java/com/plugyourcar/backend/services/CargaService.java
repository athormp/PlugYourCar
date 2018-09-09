package com.plugyourcar.backend.services;

import java.util.List;

import com.plugyourcar.backend.dto.CargaDetalleDTO;
import com.plugyourcar.backend.dto.CargaResumenDTO;
import com.plugyourcar.backend.dto.FechasReservaDTO;

public interface CargaService {
	
	void iniciarCarga(Integer idConector, Boolean cargaConReserva, FechasReservaDTO fechasReserva);
	List<CargaResumenDTO> getCargas();
	List<CargaResumenDTO> getReservas(Integer conectorId);
	CargaDetalleDTO getCarga(Integer idCarga);
	void updateCargas();
	CargaDetalleDTO finalizarCarga(Integer idCarga);
	
}
