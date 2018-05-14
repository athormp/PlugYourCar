package com.plugyourcar.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer> {

}
