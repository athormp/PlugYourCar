package com.plugyourcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.EstadoConector;

@Repository
public interface EstadoConectorRepository extends JpaRepository<EstadoConector, Integer> {

}
