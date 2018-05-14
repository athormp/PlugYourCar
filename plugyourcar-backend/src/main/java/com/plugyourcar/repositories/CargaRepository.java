package com.plugyourcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.Carga;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Integer> {

}
