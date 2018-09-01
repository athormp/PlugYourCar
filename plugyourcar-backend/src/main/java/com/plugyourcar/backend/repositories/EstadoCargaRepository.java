package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.EstadoCarga;
import com.plugyourcar.backend.model.Usuario;

@Repository
public interface EstadoCargaRepository extends JpaRepository<EstadoCarga, Integer> {
	
}
