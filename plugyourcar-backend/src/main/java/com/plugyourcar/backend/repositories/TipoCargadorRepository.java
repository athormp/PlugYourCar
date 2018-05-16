package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.TipoCargador;

@Repository
public interface TipoCargadorRepository extends JpaRepository<TipoCargador, Integer> {

	TipoCargador findOne(Integer id);
	
}
