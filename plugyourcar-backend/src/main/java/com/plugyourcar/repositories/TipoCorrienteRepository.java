package com.plugyourcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.TipoCorriente;

@Repository
public interface TipoCorrienteRepository extends JpaRepository<TipoCorriente, Integer> {

}
