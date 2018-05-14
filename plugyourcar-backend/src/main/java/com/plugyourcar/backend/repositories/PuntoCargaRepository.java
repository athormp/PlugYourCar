package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.PuntoCarga;

@Repository
public interface PuntoCargaRepository extends JpaRepository<PuntoCarga, Integer> {

}
