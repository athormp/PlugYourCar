package com.plugyourcar.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.Carga;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EstadoCarga;
import com.plugyourcar.backend.model.Usuario;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Integer> {
	
	List<Carga> findAllByUsuario(Usuario usuario);
	List<Carga> findAllByEstadoCargaIn(List<EstadoCarga> estadoCarga);
	List<Carga> findAllByConector(Conector conector);
}
