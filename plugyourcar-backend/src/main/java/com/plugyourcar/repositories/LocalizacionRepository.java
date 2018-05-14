package com.plugyourcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.Localizacion;

@Repository
public interface LocalizacionRepository extends JpaRepository<Localizacion, Integer> {

}
