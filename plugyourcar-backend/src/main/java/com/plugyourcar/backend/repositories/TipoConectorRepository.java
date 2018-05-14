package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.TipoConector;

@Repository
public interface TipoConectorRepository extends JpaRepository<TipoConector, Integer> {

}
