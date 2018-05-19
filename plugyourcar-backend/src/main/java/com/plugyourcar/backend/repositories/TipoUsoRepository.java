package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.TipoUso;

@Repository
public interface TipoUsoRepository extends JpaRepository<TipoUso, Integer> {
	
}
