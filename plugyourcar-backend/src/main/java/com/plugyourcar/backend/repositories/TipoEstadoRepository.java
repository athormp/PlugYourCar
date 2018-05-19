package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.TipoEstado;

@Repository
public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Integer> {
	
}
