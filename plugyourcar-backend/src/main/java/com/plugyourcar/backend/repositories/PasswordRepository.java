package com.plugyourcar.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Integer> {

}
