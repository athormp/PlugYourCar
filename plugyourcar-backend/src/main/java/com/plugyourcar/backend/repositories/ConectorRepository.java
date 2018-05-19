package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EquipoSuministro;

@Repository
public interface ConectorRepository extends JpaRepository<Conector, Integer> {
	
	Conector findByIdReferenciaAndEquipoSuministro(int referencia, EquipoSuministro equipoSuministro);
	
}
