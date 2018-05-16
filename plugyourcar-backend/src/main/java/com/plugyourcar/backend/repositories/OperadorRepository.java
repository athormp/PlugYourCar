package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.Operador;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Integer> {
	
	Operador findOne(Integer id);
	
}
