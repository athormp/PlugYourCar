package com.plugyourcar.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plugyourcar.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Usuario findByUserName(String dni);
	Usuario findByEmail(String email);

}
